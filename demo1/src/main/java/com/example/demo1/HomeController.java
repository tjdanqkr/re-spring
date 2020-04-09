package com.example.demo1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.python.jline.internal.InputStreamReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    Process re;
    String savename1;
    @GetMapping("/") 
    public String index(){
        return "plus"; 
    }
    @GetMapping("/api/hello") 
    public String hello(){
        return "안녕하세요. 현재 서버시간은 "+new Date() +"입니다. \n"; 
    }
    @RequestMapping(value = "/graph", method = RequestMethod.POST) 
    public String pyclo(@RequestBody final HashMap<String,Object> post,final HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("UTF-8");
        String filename=post.get("filename").toString();
        String savename=post.get("savename").toString();
        savename1= "grap/"+savename+".png";
        System.out.println(filename);
        System.out.println(savename);
        String path = "C:/react/re-st/demo1/src/main/python/";
        String filepath ="C:/react/re-st/demo1/src/main/resources/static/img/";
        String savepath = "C:/react/re-st/demo1/src/main/resources/static/grap/";
        String py = "testmd.py";
        try{

            String a= "python "+path+py+" "+filepath+filename+" "+savepath+savename;
            String cmdArray =  "cmd /c conda activate test & "+a ; 
            //실행할 프로그램과 전달할 인수를 문자열 배열로 
            String[] command = {"cmd","/c","dir"};
            System.out.println(cmdArray);
            Runtime rt = Runtime.getRuntime(); 
			Process proc = rt.exec(cmdArray); //시스템 명령어
            
			InputStream is = proc.getInputStream(); 
			InputStreamReader isr = new InputStreamReader(is); 
			BufferedReader br = new BufferedReader(isr);

			String line;
			while((line=br.readLine())!= null){
				System.out.println(line);
				
			}
            return "http://localhost:8080/grap/"+savename+".png"; 
            
        }catch(final Exception e){ System.out.println(e); }finally{ 
           System.out.println("fina");
        }
        return null; 
    }
    // @RequestMapping(value = "/graph" ,method=RequestMethod.GET) 
    // public String pyclo2(Model model){
    //     model.addAttribute("savefile", savename1);
    //     System.out.println("fs");
    //     return "print"; 
    // }
   
}