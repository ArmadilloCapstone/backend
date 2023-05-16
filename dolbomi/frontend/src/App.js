import React from 'react';
import styled from "styled-components";
import {BrowserRouter as Router, HashRouter, Route, Routes} from 'react-router-dom';

import Header from './components/Layout/Header/Header';
import TeacherSidebar from './components/Layout/Sidebar/TeacherSidebar';

import { LoginPage } from './pages/login/LoginPage';
import {TimelinePage} from './pages/timeline/TimelinePage';
import StudentState from './pages/studentstate/StudentState';
<<<<<<< HEAD
// import { EntireUserAddPage } from './pages/admin/entrire/EntireUserAddPage';
import Temp from './pages/admin/Temp/Temp';
import TeacherManagementPage from './pages/admin/teacher/TeacherManagementPage';
import StudentManagementPage from './pages/admin/student/StudentManagementPage';
import ParentManagementPage from './pages/admin/parent/ParentManagementPage';
import ClassManagementPage from './pages/admin/class/ClassManagementPage';
import AfterClassManagementPage from './pages/admin/afterClass/AfterClassManagement';
import StudentScheduleManagementPage from './pages/admin/studentSchedule/StudentScheduleManagement';
import Pickup from './pages/pickup/Pickup';
=======
=======
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
            .post("/parentPickup")
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
>>>>>>> admin_page_fix
>>>>>>> admin_page_fix2

import Footer from './components/Layout/Footer/Footer';

const Center = styled.div`
  height: 92vh;
  display: flex;
  flex-direction: row;
  `

export default function App() {
  return (
    <HashRouter>
        <Header/>
        <Center>
          <TeacherSidebar/>
          <Routes>
            <Route exact path="/" element={<LoginPage />} />
            <Route path="/TimelinePage" element={<TimelinePage />} />
            <Route path="/StudentState" element={<StudentState />} />
            {/* <Route path="/EntireUserAddPage" element={<EntireUserAddPage />} /> */}
            <Route path="/Temp" element={<Temp />} />
            <Route path="/ClassManagementPage" element={<ClassManagementPage />} />
            <Route path="/TeacherManagementPage" element={<TeacherManagementPage />} />
            <Route path="/StudentManagementPage" element={<StudentManagementPage />} />
            <Route path="/ParentManagementPage" element={<ParentManagementPage />} />
            <Route path="/AfterClassManagementPage" element={<AfterClassManagementPage />} />
            <Route path="/StudentScheduleManagementPage" element={<StudentScheduleManagementPage />} />
            <Route path="/Pickup" element={<Pickup />} />
          </Routes>
        </Center>
        {/* <Footer /> */}
      </HashRouter>
  );
}