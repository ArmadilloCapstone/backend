import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import axios from 'axios';

function App() {
  const [guardianMessage, setGuardianMessage] = useState([]);
  const [parentMessage, setParentMessage] = useState([]);
  const [parentRequest, setParentRequest] = useState([]);
  useEffect(() => {
          //getUser();
          getParent();
          getGuardian();
          getParentRequest();
      getStudentState();
  },[]);

//    async function getUser(){
//        await axios
//            .get("/hello")
//            .then((response) => {
//                console.log(response.data);
//                setMessage(response.data);
//            })
//            .catch((error)=>{
//                console.log(error);
//            })
//    }

    async function getParent(){
        await axios
            .post("/parent")
            .then((response) => {
               console.log(response.data);
               setParentMessage(response.data);
            })
            .catch((error)=>{
                console.log(error);
            })
    }

    async function getGuardian(){
            await axios
                .post("/guardian")
                .then((response) => {
                    console.log(response.data);
                    setGuardianMessage(response.data);
                })
                .catch((error)=>{
                    console.log(error);
                })
    }
    async function getParentRequest(){
            await axios
                .post("/requestParent")
                .then((response) => {
                    console.log(response.data);
                    setParentRequest(response.data);
                })
                .catch((error)=>{
                    console.log(error);
                })
   }
    async function getStudentState(){
        await axios
            .post("/changeStudentState")
            .then((response) => {
                console.log(response.data);
            })
            .catch((error)=>{
                console.log(error);
            })
    }



  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <h1> Parent Data </h1>
        <ul>
            {
                Object.entries(parentMessage).map((text,index) =>
                <li key={`${index}`}> {text[0]} : {text[1]} </li>)
            }
        </ul>
        <br/>
        <h1> Guardian Data </h1>
        <ul>
            {
                guardianMessage.map((obj, index) =>
                Object.entries(obj).map((text,idx) =>
                <li key={`${idx}`}> {text[0]} : {text[1]} </li>))
            }
        </ul>
        <br/>
        <h1> Parent Request Data </h1>
                <ul>
                    {
                        Object.entries(parentRequest).map((text,index) =>
                        <li key={`${index}`}> {text[0]} : {text[1]} </li>)
                    }
                </ul>
      </header>
    </div>
  );
}

export default App;
