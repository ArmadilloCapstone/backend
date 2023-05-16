package com.example.dolbomi.service;

import com.example.dolbomi.controller.ParentManageForm;
import com.example.dolbomi.controller.StudentScheduleForm;
import com.example.dolbomi.controller.TeacherManageForm;
import com.example.dolbomi.domain.*;
import com.example.dolbomi.repository.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AdminService {
    private final DolbomClassRepository dolbomClassRepository;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;
    private final AfterSchoolClassRepository afterSchoolClassRepository;
    private final StudentScheduleRepository studentScheduleRepository;
    private final StudentTimeRepository studentTimeRepository;

    public AdminService(DolbomClassRepository dolbomClassRepository, StudentRepository studentRepository,
                        ParentRepository parentRepository, TeacherRepository teacherRepository,
                        AfterSchoolClassRepository afterSchoolClassRepository,
                        StudentScheduleRepository studentScheduleRepository, StudentTimeRepository studentTimeRepository) {
        this.dolbomClassRepository = dolbomClassRepository;
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.teacherRepository = teacherRepository;
        this.afterSchoolClassRepository = afterSchoolClassRepository;
        this.studentScheduleRepository = studentScheduleRepository;
        this.studentTimeRepository = studentTimeRepository;
    }
    public void addNewDolbomClass(DolbomClass dolbomClass){
        List<DolbomClass> result = dolbomClassRepository.findByClassName(dolbomClass.getClass_name(), dolbomClass.getClass_num());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("기존 돌봄학급 활성화");
            dolbomClassRepository.activationDolbomClass(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1)) {
            System.out.println("이미 활성화된 돌봄학급입니다");
        } else if (result.size() == 0) {
            dolbomClass.setDisable(1L);
            dolbomClassRepository.save(dolbomClass);
        }
    }
    public void deleteDolbomClass(Long id){
        Optional<DolbomClass> result = dolbomClassRepository.findById(id);
        if((result.isPresent()) && (result.get().getDisable() == 1)){
            System.out.println("기존 돌봄학급 비활성화");
            dolbomClassRepository.disableDolbomClass(result.get().getId());
        } else if ( (result.isPresent()) && (result.get().getDisable() == 0)) {
            System.out.println("이미 비활성화된 돌봄학급입니다");
        } else {
            System.out.println("error");
        }
    }

    public List<DolbomClass> sendDolbomClassList (){
        List<DolbomClass> dolbomClassList = dolbomClassRepository.findActivationDolbomClass();
        return dolbomClassList;
    }

    public void addNewStudent(Student student){
        List<Student> result = studentRepository.findByNameGradeGender(student.getName(), student.getGrade(), student.getGender());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("기존 돌봄학생 활성화");
            studentRepository.activationStudent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 돌봄학생입니다");
        } else if (result.size() == 0) {
            System.out.println("새로운 돌봄학생 추가");
            student.setDisable(1L);
            studentRepository.save(student);
        }
    }

    public void addNewStudentByCsv(MultipartFile file){

        List<String> headerList = new ArrayList<String>();

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
            String line = "";

            while ((line = br.readLine()) != null){
                List<String> stringList = new ArrayList<String>();
                String stringArray[] = line.split(",");
                stringList = Arrays.asList(stringArray);

                if(headerList.size() ==0) {
                    headerList = stringList;
                }
                else{
                    String filename = file.getOriginalFilename();


                    if(filename.equals("inputAfterSchool.csv")){
                        AfterSchoolClass afterSchoolClass = new AfterSchoolClass();
                        afterSchoolClass.setId(Long.parseLong(stringList.get(0)));
                        afterSchoolClass.setClass_name(stringList.get(1));
                        afterSchoolClass.setStart_time(Time.valueOf(stringList.get(2)));
                        afterSchoolClass.setEnd_time(Time.valueOf(stringList.get(3)));
                        afterSchoolClass.setDay(Long.parseLong(stringList.get(4)));
                        addNewAfterSchoolClass(afterSchoolClass);
                    }
                    else if(filename.equals("inputClass.csv")){
                        DolbomClass dolbomClass = new DolbomClass();
                        dolbomClass.setId(Long.parseLong(stringList.get(0)));
                        dolbomClass.setClass_name(stringList.get(1));
                        dolbomClass.setClass_num(Long.parseLong(stringList.get(2)));
                        dolbomClass.setYear_seme(stringList.get(3));
                        dolbomClass.setDisable(Long.parseLong(stringList.get(4)));
                        addNewDolbomClass(dolbomClass);
                    }
                    else if(filename.equals("inputParent.csv")){
                        Parent parent = new Parent();
                        parent.setId(Long.parseLong(stringList.get(0)));
                        parent.setName(stringList.get(1));
                        parent.setPhone_num(stringList.get(2));
                        parent.setGender(Long.parseLong(stringList.get(3)));
                        parent.setBirth_date(java.sql.Date.valueOf(stringList.get(4)));
                        parent.setChild_id(Long.parseLong(stringList.get(5)));
                        parent.setClass_id(Long.parseLong(stringList.get(6)));
                        parent.setDisable(Long.parseLong(stringList.get(7)));
                        addNewParent(parent);
                    }
                    else if(filename.equals("inputStudent.csv")) {
                        Student student = new Student();
                        student.setId(Long.parseLong(stringList.get(0)));
                        student.setName(stringList.get(1));
                        student.setGrade(Long.parseLong(stringList.get(2)));
                        student.setPhone_num(stringList.get(3));
                        student.setGender(Long.parseLong(stringList.get(4)));
                        student.setOriginal_class_num(Long.parseLong(stringList.get(5)));
                        student.setBirth_date(java.sql.Date.valueOf(stringList.get(6)));
                        addNewStudent(student);
                    }
                    else if(filename.equals("inputStudentSchedule.csv")){
                        StudentSchedule studentSchedule = new StudentSchedule();
                        studentSchedule.setId(Long.parseLong(stringList.get(0)));
                        studentSchedule.setStudent_id(Long.parseLong(stringList.get(1)));
                        studentSchedule.setClass_id(Long.parseLong(stringList.get(2)));
                    }
                    else if(filename.equals("inputStudentTime.csv")){
                        StudentTime studentTime = new StudentTime();
                        studentTime.setStudent_id(Long.parseLong(stringList.get(0)));
                        studentTime.setStudent_id(Long.parseLong(stringList.get(1)));
                        studentTime.setEntry_1(Time.valueOf(stringList.get(2)));
                        studentTime.setEntry_2(Time.valueOf(stringList.get(3)));
                        studentTime.setEntry_3(Time.valueOf(stringList.get(4)));
                        studentTime.setEntry_4(Time.valueOf(stringList.get(5)));
                        studentTime.setEntry_5(Time.valueOf(stringList.get(6)));
                        studentTime.setOff_1(Time.valueOf(stringList.get(7)));
                        studentTime.setOff_2(Time.valueOf(stringList.get(8)));
                        studentTime.setOff_3(Time.valueOf(stringList.get(9)));
                        studentTime.setOff_4(Time.valueOf(stringList.get(10)));
                        studentTime.setOff_5(Time.valueOf(stringList.get(11)));
                        addNewStudentTime(studentTime);
                    }
                    else if(filename.equals("inputTeacher.csv")){
                        Teacher teacher = new Teacher();
                        teacher.setId(Long.parseLong(stringList.get(0)));
                        teacher.setName(stringList.get(1));
                        teacher.setPhone_num(stringList.get(2));
                        teacher.setGender(Long.parseLong(stringList.get(3)));
                        teacher.setClass_id(Long.parseLong(stringList.get(4)));
                        teacher.setBirth_date(java.sql.Date.valueOf(stringList.get(5)));
                        teacher.setDisable(Long.parseLong(stringList.get(6)));
                        addNewTeacher(teacher);
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id){
        Optional<Student> result = studentRepository.findById(id);
        if((result.isPresent()) && (result.get().getDisable() == 1)){
            System.out.println("기존 돌봄학생 비활성화");
            studentRepository.disableStudent(result.get().getId());
        } else if ( (result.isPresent()) && (result.get().getDisable() == 0) ) {
            System.out.println("이미 비활성화된 돌봄학생입니다");
        } else {
            System.out.println("error");
        }
    }

    public List<Student> sendStudentList(){
        List<Student> studentList = studentRepository.findActivationStudent();
        return studentList;
    }
    public void addNewTeacher(Teacher teacher){
        List<Teacher> result = teacherRepository.findByNameBirth(teacher.getName(), teacher.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("기존 돌봄교사 활성화 csv");
            teacherRepository.activationTeacher(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 돌봄교사입니다 csv");
        } else if (result.size() == 0) {
            System.out.println("새로운 돌봄교사 추가 csv");
            teacher.setDisable(1L);
            teacherRepository.save(teacher);
        }
    }

    public void addNewTeacherManageForm(TeacherManageForm teacherManageForm){
        List<Teacher> result = teacherRepository.findByNameBirth(teacherManageForm.getName(), teacherManageForm.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("기존 돌봄교사 활성화");
            teacherRepository.activationTeacher(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 돌봄교사입니다");
        } else if (result.size() == 0) {
            System.out.println("새로운 돌봄교사 추가");
            Teacher teacher = new Teacher();
            teacher.setId(teacherManageForm.getId());
            teacher.setName(teacherManageForm.getName());
            teacher.setPhone_num(teacherManageForm.getPhone_num());
            teacher.setGender(teacherManageForm.getGender());
            teacher.setBirth_date(teacherManageForm.getBirth_date());
            teacher.setClass_id(teacherManageForm.getClass_id());
            teacher.setDisable(1L);
            teacherRepository.save(teacher);
        }
    }

    public void deleteTeacher(Long id){
        Optional<Teacher> result = teacherRepository.findById(id);
        if((result.isPresent()) && (result.get().getDisable() == 1)){
            System.out.println("기존 돌봄교사 비활성화");
            teacherRepository.disableTeacher(result.get().getId());
        } else if ( (result.isPresent()) && (result.get().getDisable() == 0) ) {
            System.out.println("이미 비활성화된 돌봄교사입니다");
        } else {
            System.out.println("error");
        }
    }

    public List<TeacherManageForm> sendTeacherList(){
        List<Teacher> teacherList = teacherRepository.findActivationTeacher();
        List<TeacherManageForm> teacherManageFormList = new ArrayList<>();
        int count = teacherList.size();
        for(int i = 0; i < count; i++){
            TeacherManageForm teacherManageForm = new TeacherManageForm();
            teacherManageForm.setId(teacherList.get(i).getId());
            teacherManageForm.setName(teacherList.get(i).getName());
            teacherManageForm.setPhone_num(teacherList.get(i).getPhone_num());
            teacherManageForm.setGender(teacherList.get(i).getGender());
            teacherManageForm.setBirth_date(teacherList.get(i).getBirth_date());
            teacherManageForm.setClass_id(teacherList.get(i).getClass_id());
            teacherManageForm.setClass_name(dolbomClassRepository.findById(teacherList.get(i).getClass_id()).get().getClass_name());
            teacherManageForm.setDisable(teacherList.get(i).getDisable());
            teacherManageFormList.add(teacherManageForm);
        }
        return teacherManageFormList;
    }

    public void addNewParent(Parent parent){
        List<Parent> result = parentRepository.findByNameBirth(parent.getName(), parent.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable()==0)){
            System.out.println("기존 학부모 활성화 csv");
            parentRepository.activationParent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 학부모입니다 csv");
        } else if (result.size() == 0) {
            System.out.println("새로운 학부모 추가 csv");
            parent.setDisable(1L);
            parentRepository.save(parent);
        }
    }

    public  void addNewParentManageForm(ParentManageForm parentManageForm){
        List<Parent> result = parentRepository.findByNameBirth(parentManageForm.getName(), parentManageForm.getBirth_date());
        if((result.size()==1)&&(result.get(0).getDisable()==0)){
            System.out.println("기존 학부모 활성화");
            parentRepository.activationParent(result.get(0).getId());
        } else if ((result.size() == 1) && (result.get(0).getDisable() == 1)) {
            System.out.println("이미 활성화된 학부모입니다");
        } else if (result.size()==0) {
            System.out.println("새로운 학부모 추가");
            Parent parent = new Parent();
            parent.setId(parentManageForm.getId());
            parent.setName(parentManageForm.getName());
            parent.setPhone_num(parentManageForm.getPhone_num());
            parent.setGender(parentManageForm.getGender());
            parent.setBirth_date(parentManageForm.getBirth_date());
            parent.setChild_id(parentManageForm.getChild_id());
            parent.setClass_id(studentRepository.findById(parentManageForm.getChild_id()).get().getClass_id());
            parent.setDisable(1L);
            parentRepository.save(parent);
        }
    }

    public void deleteParent(Long id){
        Optional<Parent> result = parentRepository.findById(id);
        if((result.isPresent()) && (result.get().getDisable()==1)){
            System.out.println("기존 학부모 비활성화");
            parentRepository.disableParent(result.get().getId());
        } else if ( (result.isPresent()) && (result.get().getDisable() == 0) ) {
            System.out.println("이미 비활성화된 학부모입니다");
        } else {
            System.out.println("error");
        }
    }

    public List<ParentManageForm> sendParentList(){
        List<Parent> parentList = parentRepository.findActivationParent();
        List<ParentManageForm> parentManageFormList = new ArrayList<>();
        int count = parentList.size();
        for(int i = 0; i<count;i++){
            ParentManageForm parentManageForm = new ParentManageForm();
            parentManageForm.setId(parentList.get(i).getId());
            parentManageForm.setName(parentList.get(i).getName());
            parentManageForm.setPhone_num(parentList.get(i).getPhone_num());
            parentManageForm.setGender(parentList.get(i).getGender());
            parentManageForm.setBirth_date(parentList.get(i).getBirth_date());
            parentManageForm.setChild_name(studentRepository.findById(parentList.get(i).getChild_id()).get().getName());
            parentManageForm.setChild_id(parentList.get(i).getChild_id());
            parentManageFormList.add(parentManageForm);
        }

        return parentManageFormList;
    }

    public void addNewAfterSchoolClass(AfterSchoolClass afterSchoolClass){
        List<AfterSchoolClass> result = afterSchoolClassRepository.findByClass_nameDay(afterSchoolClass.getClass_name(), afterSchoolClass.getDay());
        if(result.size() == 1){
            System.out.println("이미 존재하는 방과후활동입니다");
        } else if (result.size() == 0) {
            System.out.println("새로운 방과후활동 추가");
            afterSchoolClassRepository.save(afterSchoolClass);
        }
    }
    
    //학생 스케줄 먼저 구현하고 나머지 구현할것
    public int deleteAfterSchoolClass(Long id){
        Optional<AfterSchoolClass> result = afterSchoolClassRepository.findById(id);
        if(result.isPresent()){
            Long class_id = result.get().getId();
            List<StudentSchedule> studentScheduleList = studentScheduleRepository.findByClass_id(class_id);
            if(studentScheduleList.size() > 0){
                System.out.println("해당 방과후활동을 듣고있는 학생이 있으므로 삭제할 수 없습니다");
                return 0;
            } else if (studentScheduleList.size() == 0) {
                System.out.println("방과후활동 삭제");
                afterSchoolClassRepository.deleteAfterSchoolClass(class_id);
                return 1;
            }
        }
        return 2;
    }
    public List<AfterSchoolClass> sendAfterSchoolClassList(){
        List<AfterSchoolClass> afterSchoolClassList = afterSchoolClassRepository.findAll();
        return afterSchoolClassList;
    }

    public void addNewStudentSchedule(StudentScheduleForm studentScheduleForm){
        List<StudentSchedule> result = studentScheduleRepository.findByStudent_idClass_id(studentScheduleForm.getStudent_id(), studentScheduleForm.getClass_id());
        if(result.size() == 1){
            System.out.println("이미 존재하는 학생스케줄입니다");
        } else if (result.size()==0) {
            System.out.println("새로운 학생스케줄 추가");
            StudentSchedule studentSchedule = new StudentSchedule();
            studentSchedule.setClass_id(studentScheduleForm.getClass_id());
            studentSchedule.setStudent_id(studentScheduleForm.getStudent_id());
            studentScheduleRepository.save(studentSchedule);
        }
    }

    public void deleteStudentSchedule(Long id){
        Optional<StudentSchedule> result = studentScheduleRepository.findById(id);
        if(result.isPresent()){
            System.out.println("학생스케줄 삭제");
            studentScheduleRepository.deleteStudentSchedule(id);
        } else {
            System.out.println("학생스케줄이 존재하지 않아 삭제할 수 없습니다");
        }
    }

    public List<StudentScheduleForm> sendStudentSchedule(){
        List<StudentSchedule> studentScheduleList = studentScheduleRepository.findAll();
        List<StudentScheduleForm> studentScheduleFormList = new ArrayList<>();
        int count = studentScheduleList.size();
        for(int i = 0; i < count; i++){
            StudentScheduleForm studentScheduleForm = new StudentScheduleForm();
            studentScheduleForm.setId(studentScheduleList.get(i).getId());
            studentScheduleForm.setName(studentRepository.findById(studentScheduleList.get(i).getStudent_id()).get().getName());
            studentScheduleForm.setStudent_id(studentScheduleList.get(i).getStudent_id());
            studentScheduleForm.setClass_name(afterSchoolClassRepository.findById(studentScheduleList.get(i).getClass_id()).get().getClass_name());
            studentScheduleForm.setClass_id(studentScheduleList.get(i).getClass_id());
            studentScheduleFormList.add(studentScheduleForm);
        }
        return studentScheduleFormList;
    }

    public void addNewStudentTime(StudentTime studentTime){
        List<StudentTime> result = studentTimeRepository.findByStudent_id(studentTime.getStudent_id());
        if(result.size() == 1){
            System.out.println("이미 존재하는 학생돌봄시간입니다");
        } else if (result.size()==0) {
            System.out.println("돌봄학생의 돌봄시간 생성");
            studentTimeRepository.save(studentTime);
        }
    }
    public void deleteStudentTime(Long id){
        Optional<StudentTime> result = studentTimeRepository.findById(id);
        if(result.isPresent()){
            System.out.println("학생돌봄시간 삭제");
            studentTimeRepository.deleteStudentTime(id);
        } else {
            System.out.println("학생돌봄시간이 존재하지 않아 삭제할 수 없습니다");
        }
    }
    public List<StudentTime> sendStudentTimeList(){
        List<StudentTime> studentTimeList = studentTimeRepository.findAll();
        return studentTimeList;
    }
}
