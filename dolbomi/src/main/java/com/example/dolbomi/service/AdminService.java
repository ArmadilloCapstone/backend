package com.example.dolbomi.service;

import com.example.dolbomi.domain.*;
import com.example.dolbomi.repository.*;
import org.aspectj.lang.annotation.After;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AdminService {
    private final DolbomClassRepository dolbomClassRepository;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;

    public AdminService(DolbomClassRepository dolbomClassRepository, StudentRepository studentRepository,
                        ParentRepository parentRepository, TeacherRepository teacherRepository) {
        this.dolbomClassRepository = dolbomClassRepository;
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.teacherRepository = teacherRepository;
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

                    if(filename.equals("inputStudent.csv")) {
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
                    else if(filename.equals("inputAfterSchool.csv")){
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
            System.out.println("기존 돌봄교사 활성화");
            teacherRepository.activationTeacher(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 돌봄교사입니다");
        } else if (result.size() == 0) {
            System.out.println("새로운 돌봄교사 추가");
            teacher.setDisable(1L);
            teacherRepository.save(teacher);
        }
    }

    public void deleteTeacher(Teacher teacher){
        List<Teacher> result = teacherRepository.findByNameBirth(teacher.getName(), teacher.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 1)){
            System.out.println("기존 돌봄교사 비활성화");
            teacherRepository.disableTeacher(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 0) ) {
            System.out.println("이미 비활성화된 돌봄교사입니다");
        } else if (result.size()==0) {
            System.out.println("error");
        }
    }

    public List<Teacher> sendTeacherList(){
        List<Teacher> teacherList = teacherRepository.findActivationTeacher();
        return teacherList;
    }

    public void addNewParent(Parent parent){
        List<Parent> result = parentRepository.findByNameBirth(parent.getName(), parent.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable()==0)){
            System.out.println("기존 학부모 활성화");
            parentRepository.activationParent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("이미 활성화된 학부모입니다");
        } else if (result.size() == 0) {
            System.out.println("새로운 학부모 추가");
            parent.setDisable(1L);
            parentRepository.save(parent);
        }
    }

    public void deleteParent(Parent parent){
        List<Parent> result = parentRepository.findByNameBirth(parent.getName(), parent.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable()==1)){
            System.out.println("기존 학부모 비활성화");
            parentRepository.disableParent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 0) ) {
            System.out.println("이미 비활성화된 학부모입니다");
        } else if (result.size() == 0) {
            System.out.println("error");
        }
    }

    public List<Parent> sendParentList(){
        List<Parent> parentList = parentRepository.findActivationParent();
        return parentList;
    }

}
