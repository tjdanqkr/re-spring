import React,{useState,useEffect, useCallback} from 'react';
import axios from 'axios';
import {useForm} from 'react-hook-form';
import { Link } from 'react-router-dom';
import './cs.css';
const Index = () => {

    const api= axios.create({
        baseURL:'http://localhost:8080/'
    })
    const [filename, setFilename]= useState('');
    const [savename, setSavename] = useState('');
    const [graphname,setgraphname] = useState('');
    const [plag,setPlag] = useState(false);
    const {register, handleSubmit, reset, errors} = useForm();
    const onChangesave = (e => {
        setSavename=e.target.value;
    })
    const onChangefile = (e => {
        
        setFilename=e.target.value;
    })
    
    const OnClick =(e) =>{
        console.log(plag);
        
        if (plag===true) {
            
            setPlag(false);
        }else if(plag===false){
            setPlag(true); 
        }
    }
    const OnSubmit = (
        (e,data )=> {
            console.log(filename);
            console.log(savename);
            // submit 이벤트는 브라우저에서 새로고침을 발생시킵니다.
            // 이를 방지하기 위하여 이 함수를 호출합니다.
            const post ={
                filename:filename,
                savename:savename
            };
            
            fetch('/graph',{
                method:"POST",
                headers:{
                    'content-type':'application/json'
                },
                body: JSON.stringify(post)
            }) 
            .then(response => response.text()) 
            .then(message => { 
                 console.log(message);
                setgraphname(message);
                console.log(graphname);
            })
        }
    );
    
    
    return (
        
        <div className="index">
            <form onSubmit={handleSubmit(OnSubmit)} >
                <input type="text" name="filename" placeholder="파일명.확장자" onChange={e=>setFilename(e.target.value)} ></input>
                <input type="text" name="savename" placeholder="세이브명" onChange={e=>setSavename(e.target.value)} ></input>
                <input type="submit" value="전송"></input>
            </form>
            <button onClick={OnClick}>확인</button>
           {(plag?(graphname!==""?<img src={graphname}></img>:<h1>아직입니다</h1>):null)}
            {/* <img src='http://localhost:8080/grap/x.png' ></img> */}
            
        </div>
    );
}

export default Index;
