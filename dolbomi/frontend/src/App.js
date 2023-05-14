import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import axios from 'axios';

function App() {

  const [parentMessage, setParentMessage] = useState([]);
  const [guardianMessage, setGuardianMessage] = useState([]);
  const [parentRequest, setParentRequest] = useState([]);
  const [guardianRequest, setGuardianRequest] = useState([]);
  const [teacherRequest, setTeacherRequest] = useState([]);
  const [adminAddDolbomClassRequest, setAdminDolbomClassRequest] = useState([]);


  var id = 15;
  var title = "helloTestTitle";

  var name = "강강술래";
  var gender = 1;
  var grade = 2;
  var clientId = 107;

  var num = 783;

  var studentId = 978;
  var studentName = "오규민";
  var studentGrade = 4;
  var studentPhone_num = "010 - 1234 - 5678";

  var teacherId = 2107;

  var dolbomClassName = "장미반";
  var dolbomClassNum = 1;
  var dolbomClassYear_seme = "2023년-2학기";
  useEffect(() => {
          //getUser();
          getParent();
          getGuardian();
          getParentRequest();
          getGuardianRequest();
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
   async function getGuardianRequest(){
               await axios
                   .post("/requestGuardian")
                   .then((response) => {
                       console.log(response.data);
                       setGuardianRequest(response.data);
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
        <h1> Test1 </h1>
        <ul>
            <li key = {parentMessage.id}> {parentMessage.name}, {parentMessage.grade}, {parentMessage.gender} </li>
            {
              guardianMessage.map((obj, index) =>
              <li key={`${index}`}> {obj.id} : {obj.name} : {obj.grade} : {obj.gender} </li>)
            }
            <li>
                {parentRequest.pickupManId} : {parentRequest.pickupManName} : {parentRequest.studentId}
                : {parentRequest.studentName} : {parentRequest.studentGrade} : {parentRequest.studentGender}
            </li>
            {
                guardianRequest.map((obj, index) =>
                <li key={`${index}`}> {obj.pickupManId} : {obj.pickupManName} : {obj.studentId} : {obj.studentName} :
                 {obj.studentGrade} : {obj.studentGender}</li>)
            }
        </ul>
        <h1> Test2 </h1>
        <button onClick = { () => {
            axios.get("/urlTest", {
                params:{
                    id : id,
                    title : title
                }
            }).catch(function(){
                console.log("fail")
            })
        }}> 전송1 </button>

        <button onClick = { () => {
            axios.get("/urlTest2",{
                params:{
                    clientId : clientId,
                    id : id,
                    name : name,
                    grade : grade,
                    gender : gender
                }
            }).catch(function(){
                console.log("fail!")
            })
        }}> 전송2 </button>

        <button onClick = { () => {
            axios.get("/sendGet", {
                params:{
                    num : num
                }
            }).then(function(response){
                console.log(response.data);
            }).catch(function(){
                console.log("fail!")
            })
        }}> 전송3 </button>

        <button onClick = { () => {
            axios.post("/postTest", {
                id : studentId,
                name : studentName,
                grade : studentGrade,
                phone_num : studentPhone_num,
            }).then(function(response){
                console.log(response.data);
            }).catch(function(){
                console.log("fail!")
            })
        }}> 전송4 </button>

        <button onClick ={ () => {
            axios.post("/sendTeacher", {
                id : teacherId,
            }).then(function(response){
                console.log(response.data);
                setTeacherRequest(response.data);
            }).catch(function(){
                console.log("fail!")
            })
        }}> teacherSendbutton </button>

        <button onClick = { () => {
            axios.post("/adminAddNewDolbomClass", {
                class_name : dolbomClassName,
                class_num : dolbomClassNum,
                year_seme : dolbomClassYear_seme,
            }).then(function(response){
                console.log(response.data);
                setAdminDolbomClassRequest(response.data);
            }).catch(function(){
                console.log("fail!");
            })
        }}> AddNewDolbomClass </button>

        <button onClick = { () => {
            axios.post("/adminDeleteDolbomClass", {
                class_name : dolbomClassName,
                class_num : dolbomClassNum,
                year_seme : dolbomClassYear_seme,
            }).then(function(response){
                console.log(response.data);
            })
        }}> DeleteDolbomClass </button>

        <h1> Teacher Data </h1>
        {
            teacherRequest.map((obj, index) =>
            <li key = {`${index}`}> {obj.pickupManId} : {obj.pickupManName} : {obj.studentId} : {obj.studentName} :
            {obj.studentGrade} : {obj.studentGender} </li>)
        }

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
        <br/>
        <h1> Guardian Request Data </h1>
        <ul>
            {
                guardianRequest.map((obj, index) =>
                Object.entries(obj).map((text,idx) =>
                <li key={`${idx}`}> {text[0]} : {text[1]} </li>))
            }
        </ul>
      </header>
    </div>
  );
}

export default App;
