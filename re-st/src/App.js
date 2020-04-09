import React,{useState,useEffect} from 'react';
import {Route } from 'react-router-dom';
import './App.css';
import Index from './page/index';
import Graph from './page/graph';

const App=() =>{
  const [message, setMessage] = useState("");
  useEffect(() => {
     fetch('/api/hello') .then(response => response.text()) .then(message => { 
       setMessage(message); 
    }); 
  },[])
  return (
    <>
      <Route path="/" component={Index}></Route>
      <Route path="/graph" component={Graph} message={message}></Route>
    </>
  );
}

export default App;
