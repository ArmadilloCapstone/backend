package com.example.dolbomi.service;

import com.example.dolbomi.controller.*;
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
    private final StudentStateRepository studentStateRepository;

    public AdminService(DolbomClassRepository dolbomClassRepository, StudentRepository studentRepository,
                        ParentRepository parentRepository, TeacherRepository teacherRepository,
                        AfterSchoolClassRepository afterSchoolClassRepository, StudentScheduleRepository studentScheduleRepository,
                        StudentTimeRepository studentTimeRepository, StudentStateRepository studentStateRepository) {
        this.dolbomClassRepository = dolbomClassRepository;
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.teacherRepository = teacherRepository;
        this.afterSchoolClassRepository = afterSchoolClassRepository;
        this.studentScheduleRepository = studentScheduleRepository;
        this.studentTimeRepository = studentTimeRepository;
        this.studentStateRepository = studentStateRepository;
    }
    public void addNewDolbomClass(DolbomClass dolbomClass){
        List<DolbomClass> byNameNum = dolbomClassRepository.findByClassNameClassNum(dolbomClass.getClass_name(), dolbomClass.getClass_num());
        List<DolbomClass> byName = dolbomClassRepository.findByClassName(dolbomClass.getClass_name());
        List<DolbomClass> byNum = dolbomClassRepository.findByClassNum(dolbomClass.getClass_num());
        if( (byNameNum.size()==1) && (byNum.size() == 1) && (byName.size() == 1) && (byNameNum.get(0).getDisable() == 0)){
            System.out.println("Activation of existing dolbom class");
            dolbomClassRepository.activationDolbomClass(byNameNum.get(0).getId());
            dolbomClassRepository.updateDolbomClass(byNameNum.get(0).getId(),byNameNum.get(0).getYear_seme());
        } else if ( (byNameNum.size()==1) && (byNum.size() == 1) && (byName.size() == 1) && (byNameNum.get(0).getDisable() == 1)) {
            System.out.println("This dolbom class is already active");
        } else if ( (byNameNum.size() == 0) && (byNum.size() == 0) && (byName.size() == 0)) {
            System.out.println("Add new dolbom class");
            dolbomClass.setDisable(1L);
            dolbomClassRepository.save(dolbomClass);
        } else{
            System.out.println("A dolbom class with the same class name but a different class number already exists");
        }
    }

    public void deleteDolbomClass(Long id){
        Optional<DolbomClass> result = dolbomClassRepository.findById(id);
        if((result.isPresent()) && (result.get().getDisable() == 1)){
            System.out.println("Disable the existing dolbom class");
            dolbomClassRepository.disableDolbomClass(result.get().getId());
        } else if ( (result.isPresent()) && (result.get().getDisable() == 0)) {
            System.out.println("This dolbom class is already inactive");
        } else {
            System.out.println("error in deleteDolbomClass");
        }
    }

    public List<DolbomClass> sendDolbomClassList (){
        List<DolbomClass> dolbomClassList = dolbomClassRepository.findActivationDolbomClass();
        return dolbomClassList;
    }

    public List<Student> sendOriginal_class_num(){
        List<Student> studentList = new ArrayList<>();
        for(Long i = 1L; i < 12L; i++){
            Student student = new Student();
            student.setOriginal_class_num(i);
            studentList.add(student);
        }
        return studentList;
    }

    public void addNewStudent(Student student){
        List<Student> result = studentRepository.findByNameBirth(student.getName(), student.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("Activation of existing dolbom student by csv");
            studentRepository.activationStudent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("This dolbom student is already active by csv");
        } else if (result.size() == 0) {
            System.out.println("Add new dolbom student by csv");
            student.setDisable(1L);
            Student registerdStudent = studentRepository.save(student);
            StudentState studentState = new StudentState();
            studentState.setStudent_id(registerdStudent.getId());
            studentState.setState(1L);
            studentStateRepository.save(studentState);
        }
    }
    public void addNewStudentManageForm(StudentManageForm studentManageForm){
        Long gender = 0L;
        if(studentManageForm.getGender().equals("남")){
            gender = 1L;
        } else if (studentManageForm.getGender().equals("여")) {
            gender = 2L;
        } else {
            System.out.println("Gender doesn't exist");
            return;
        }
        List<Student> result = studentRepository.findByNameBirth(studentManageForm.getName(), studentManageForm.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("Activation of existing dolbom student");
            studentRepository.activationStudent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("This dolbom studnet is already active");
        } else if (result.size() == 0) {
            System.out.println("Add new dolbom student");
            Student student = new Student();
            student.setId(studentManageForm.getId());
            student.setName(studentManageForm.getName());
            student.setGrade(studentManageForm.getGrade());
            student.setPhone_num(studentManageForm.getPhone_num());
            student.setGender(gender);
            List<DolbomClass> isDolbomClass = dolbomClassRepository.findByClassName(studentManageForm.getClass_name());
            if(isDolbomClass.size() == 1){
                student.setClass_id(isDolbomClass.get(0).getId());
            } else if (isDolbomClass.size() == 0){
                System.out.println("There is no that kinds of dolbom class in addNewStudentManageForm");
            } else {
                System.out.println("Duplicate dolbom class in addNewStudentManageForm");
                student.setClass_id(isDolbomClass.get(0).getId());
            }
            student.setBirth_date(studentManageForm.getBirth_date());
            student.setDisable(1L);
            student.setOriginal_class_num(studentManageForm.getOriginal_class_num());
            Student registerdStudent = studentRepository.save(student);
            StudentState studentState = new StudentState();
            studentState.setStudent_id(registerdStudent.getId());
            studentState.setState(1L);
            studentStateRepository.save(studentState);
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


                    if(filename.equals("5.inputAfterSchool.csv")){
                        AfterSchoolClass afterSchoolClass = new AfterSchoolClass();
                        afterSchoolClass.setId(Long.parseLong(stringList.get(0)));
                        afterSchoolClass.setClass_name(stringList.get(1));
                        afterSchoolClass.setStart_time(Time.valueOf(stringList.get(2)));
                        afterSchoolClass.setEnd_time(Time.valueOf(stringList.get(3)));
                        afterSchoolClass.setDay(Long.parseLong(stringList.get(4)));
                        addNewAfterSchoolClass(afterSchoolClass);
                    }
                    else if(filename.equals("1.inputClass.csv")){
                        DolbomClass dolbomClass = new DolbomClass();
                        dolbomClass.setId(Long.parseLong(stringList.get(0)));
                        dolbomClass.setClass_name(stringList.get(1));
                        dolbomClass.setClass_num(Long.parseLong(stringList.get(2)));
                        dolbomClass.setYear_seme(stringList.get(3));
                        dolbomClass.setDisable(Long.parseLong(stringList.get(4)));
                        addNewDolbomClass(dolbomClass);
                    }
                    else if(filename.equals("4.inputParent.csv")){
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
                    else if(filename.equals("2.inputStudent.csv")) {
                        Student student = new Student();
                        student.setId(Long.parseLong(stringList.get(0)));
                        student.setName(stringList.get(1));
                        student.setGrade(Long.parseLong(stringList.get(2)));
                        student.setPhone_num(stringList.get(3));
                        student.setGender(Long.parseLong(stringList.get(4)));
                        student.setClass_id(Long.parseLong(stringList.get(5)));
                        student.setOriginal_class_num(Long.parseLong(stringList.get(6)));
                        student.setBirth_date(java.sql.Date.valueOf(stringList.get(7)));
                        addNewStudent(student);
                    }
                    else if(filename.equals("7.inputStudentSchedule.csv")){
                        StudentSchedule studentSchedule = new StudentSchedule();
                        studentSchedule.setId(Long.parseLong(stringList.get(0)));
                        studentSchedule.setStudent_id(Long.parseLong(stringList.get(1)));
                        studentSchedule.setClass_id(Long.parseLong(stringList.get(2)));
                        studentScheduleRepository.save(studentSchedule);
                    }
                    else if(filename.equals("6.inputStudentTime.csv")){
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
                    else if(filename.equals("3.inputTeacher.csv")){
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
            System.out.println("Disable the existing dolbom student");
            studentRepository.disableStudent(result.get().getId());
        } else if ( (result.isPresent()) && (result.get().getDisable() == 0) ) {
            System.out.println("This dolbom student is already inactive");
        } else {
            System.out.println("error in deleteStudent");
        }
    }

    public List<Student> sendStudentList(){
        List<Student> studentList = studentRepository.findActivationStudent();
        return studentList;
    }
    public List<StudentManageForm> sendStudentManageFormList(){
        List<Student> studentList = studentRepository.findActivationStudent();
        List<StudentManageForm> studentManageFormList = new ArrayList<>();
        int count = studentList.size();
        for(int i = 0;i<count;i++){
            StudentManageForm studentManageForm = new StudentManageForm();
            studentManageForm.setId(studentList.get(i).getId());
            studentManageForm.setName(studentList.get(i).getName());
            studentManageForm.setGrade(studentList.get(i).getGrade());
            studentManageForm.setPhone_num(studentList.get(i).getPhone_num());
            Long gender = studentList.get(i).getGender();
            if(gender == 1L) {
                studentManageForm.setGender("남");
            } else if (gender == 2L) {
                studentManageForm.setGender("여");
            } else {
                System.out.println("Gender value error stored in DB");
                studentManageForm.setGender("error in sendStudentManageFormList");
            }
            studentManageForm.setClass_name(dolbomClassRepository.findById(studentList.get(i).getClass_id()).get().getClass_name());
            studentManageForm.setBirth_date(studentList.get(i).getBirth_date());
            studentManageForm.setOriginal_class_num(studentList.get(i).getOriginal_class_num());
            studentManageFormList.add(studentManageForm);
        }
        return studentManageFormList;
    }

    public void addNewTeacher(Teacher teacher){
        System.out.println(teacher.getName());
        List<Teacher> result = teacherRepository.findByNameBirth(teacher.getName(), teacher.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("Activation of existing dolbom teacher by csv");
            teacherRepository.activationTeacher(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("This dolbom teacher is already active by csv");
        } else if (result.size() == 0) {
            System.out.println("Add new dolbom teacher csv");
            teacher.setDisable(1L);
            teacherRepository.save(teacher);
        }
    }

    public void addNewTeacherManageForm(TeacherManageForm teacherManageForm){
        Long gender = 0L;
        if(teacherManageForm.getGender().equals("남")){
            gender = 1L;
        } else if (teacherManageForm.getGender().equals("여")) {
            gender = 2L;
        } else {
            System.out.println("Gender doesn't exist");
            return;
        }
        List<Teacher> result = teacherRepository.findByNameBirth(teacherManageForm.getName(), teacherManageForm.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable() == 0)){
            System.out.println("Activation of existing dolbom teacher");
            teacherRepository.activationTeacher(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("This dolbom teacher is already active");
        } else if (result.size() == 0) {
            System.out.println("Add new dolbom teacher");
            Teacher teacher = new Teacher();
            teacher.setId(teacherManageForm.getId());
            teacher.setName(teacherManageForm.getName());
            teacher.setPhone_num(teacherManageForm.getPhone_num());
            teacher.setGender(gender);
            teacher.setBirth_date(teacherManageForm.getBirth_date());
            List<DolbomClass> isDolbomClass = dolbomClassRepository.findByClassName(teacherManageForm.getClass_name());
            if(isDolbomClass.size() == 1){
                teacher.setClass_id(isDolbomClass.get(0).getId());
            } else if (isDolbomClass.size() == 0){
                System.out.println("There is no that kinds of dolbom class in addNewTeacherManageForm");
            } else {
                System.out.println("Duplicate dolbom class in addNewTeacherManageForm");
                teacher.setClass_id(isDolbomClass.get(0).getId());
            }
            teacher.setDisable(1L);
            teacherRepository.save(teacher);
        }
    }

    public void deleteTeacher(Long id){
        Optional<Teacher> result = teacherRepository.findById(id);
        if((result.isPresent()) && (result.get().getDisable() == 1)){
            System.out.println("Disable the existing dolbom teacher");
            teacherRepository.disableTeacher(result.get().getId());
        } else if ( (result.isPresent()) && (result.get().getDisable() == 0) ) {
            System.out.println("This dolbom teacher is already inactive.");
        } else {
            System.out.println("error in deleteTeacher");
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
            Long gender = teacherList.get(i).getGender();
            if(gender == 1L) {
                teacherManageForm.setGender("남");
            } else if (gender == 2L) {
                teacherManageForm.setGender("여");
            } else {
                System.out.println("Gender value error stored in DB");
                teacherManageForm.setGender("error in sendTeacherList");
            }
            teacherManageForm.setBirth_date(teacherList.get(i).getBirth_date());
            teacherManageForm.setClass_name(dolbomClassRepository.findById(teacherList.get(i).getClass_id()).get().getClass_name());
            teacherManageFormList.add(teacherManageForm);
        }
        return teacherManageFormList;
    }

    public void addNewParent(Parent parent){
        List<Parent> result = parentRepository.findByNameBirth(parent.getName(), parent.getBirth_date());
        if((result.size() == 1) && (result.get(0).getDisable()==0)){
            System.out.println("Activation of existing parent by csv");
            parentRepository.activationParent(result.get(0).getId());
        } else if ( (result.size() == 1) && (result.get(0).getDisable() == 1) ) {
            System.out.println("This parent is already active by csv");
        } else if (result.size() == 0) {
            System.out.println("Add new parent csv");
            parent.setDisable(1L);
            parentRepository.save(parent);
        }
    }

    public  void addNewParentManageForm(ParentManageForm parentManageForm){
        Long gender = 0L;
        if(parentManageForm.getGender().equals("남")){
            gender = 1L;
        } else if (parentManageForm.getGender().equals("여")) {
            gender = 2L;
        } else {
            System.out.println("Gender doesn't exist");
            return;
        }
        List<Parent> result = parentRepository.findByNameBirth(parentManageForm.getName(), parentManageForm.getBirth_date());
        if((result.size()==1)&&(result.get(0).getDisable()==0)){
            System.out.println("Activation of existing parent");
            parentRepository.activationParent(result.get(0).getId());
        } else if ((result.size() == 1) && (result.get(0).getDisable() == 1)) {
            System.out.println("This parent is already active");
        } else if (result.size()==0) {
            System.out.println("Add new parent");
            Parent parent = new Parent();
            parent.setId(parentManageForm.getId());
            parent.setName(parentManageForm.getName());
            parent.setPhone_num(parentManageForm.getPhone_num());
            parent.setGender(gender);
            parent.setBirth_date(parentManageForm.getBirth_date());
            List<Student> isStudent = studentRepository.findByName(parentManageForm.getChild_name());
            if(isStudent.size()==1){
                parent.setChild_id(isStudent.get(0).getId());
            } else if (isStudent.size()==0) {
                System.out.println("There is no that kinds of dolbom student in addNewParentManageForm");
            } else{
                System.out.println("Duplicate dolbom student in addNewParentManageForm");
            }
            Optional<DolbomClass> isDolbomClass = dolbomClassRepository.findById(isStudent.get(0).getClass_id());
            if(isDolbomClass.isPresent()){
                parent.setClass_id(isDolbomClass.get().getId());
            } else {
                System.out.println("There is no dolbom class in addNewParentManageForm");
            }
            parent.setDisable(1L);
            parentRepository.save(parent);
        }
    }

    public void deleteParent(Long id){
        Optional<Parent> result = parentRepository.findById(id);
        if((result.isPresent()) && (result.get().getDisable()==1)){
            System.out.println("Disable the existing parent");
            parentRepository.disableParent(result.get().getId());
        } else if ( (result.isPresent()) && (result.get().getDisable() == 0) ) {
            System.out.println("This parent is already inactive");
        } else {
            System.out.println("error in deleteParent");
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
            Long gender = parentList.get(i).getGender();
            if(gender == 1L){
                parentManageForm.setGender("남");
            } else if (gender == 2L) {
                parentManageForm.setGender("여");
            } else {
                System.out.println("DB error in sendParentList");
                parentManageForm.setGender("error in sendParentlist");
            }
            parentManageForm.setBirth_date(parentList.get(i).getBirth_date());
            parentManageForm.setChild_name(studentRepository.findById(parentList.get(i).getChild_id()).get().getName());
            parentManageFormList.add(parentManageForm);
        }
        return parentManageFormList;
    }

    public void addNewAfterSchoolClass(AfterSchoolClass afterSchoolClass){
        List<AfterSchoolClass> result = afterSchoolClassRepository.findByClass_nameDay(afterSchoolClass.getClass_name(), afterSchoolClass.getDay());
        if(result.size() == 1){
            System.out.println("This AfterSchoolClass is already existing");
        } else if (result.size() == 0) {
            System.out.println("Add new AfterSchoolClass");
            afterSchoolClassRepository.save(afterSchoolClass);
        }
    }

    public void addNewAfterSchoolClassManageForm(AfterSchoolClassManageForm afterSchoolClassManageForm){
        Long day = 0L;
        if(afterSchoolClassManageForm.getDay().equals("월")){
            day = 1L;
        } else if (afterSchoolClassManageForm.getDay().equals("화")) {
            day = 2L;
        } else if (afterSchoolClassManageForm.getDay().equals("수")) {
            day = 3L;
        } else if (afterSchoolClassManageForm.getDay().equals("목")) {
            day = 4L;
        } else if (afterSchoolClassManageForm.getDay().equals("금")) {
            day = 5L;
        } else {
            System.out.println("Day doesn't exist");
            return;
        }
        List<AfterSchoolClass> result = afterSchoolClassRepository.findByClassName(afterSchoolClassManageForm.getClass_name());
        if(result.size() == 1){
            System.out.println("This AfterSchoolClass is already existing");
        } else if (result.size() == 0) {
            System.out.println("Add new AfterSchoolClass");
            AfterSchoolClass afterSchoolClass = new AfterSchoolClass();
            afterSchoolClass.setId(afterSchoolClassManageForm.getId());
            afterSchoolClass.setClass_name(afterSchoolClassManageForm.getClass_name());
            if((afterSchoolClassManageForm.getStart_time().isAfter(afterSchoolClassManageForm.getEnd_time())) ||
            afterSchoolClassManageForm.getStart_time().equals(afterSchoolClassManageForm.getEnd_time())){
                System.out.println("start time is equal or after than end time");
                return;
            }
            afterSchoolClass.setStart_time(Time.valueOf(afterSchoolClassManageForm.getStart_time()));
            afterSchoolClass.setEnd_time(Time.valueOf(afterSchoolClassManageForm.getEnd_time()));
            afterSchoolClass.setDay(day);
            afterSchoolClassRepository.save(afterSchoolClass);
        }

    }

    public int deleteAfterSchoolClass(Long id){
        Optional<AfterSchoolClass> result = afterSchoolClassRepository.findById(id);
        if(result.isPresent()){
            Long class_id = result.get().getId();
            List<StudentSchedule> studentScheduleList = studentScheduleRepository.findByClass_id(class_id);
            if(studentScheduleList.size() > 0){
                System.out.println("Cannot delete because there are students listening to the AfterSchoolClass");
                return 0;
            } else if (studentScheduleList.size() == 0) {
                System.out.println("Delete AfterSchoolClass");
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

    public List<AfterSchoolClassManageForm> sendAfterSchoolClassManageForm(){
        List<AfterSchoolClassManageForm> afterSchoolClassManageFormList = new ArrayList<>();
        List<AfterSchoolClass> afterSchoolClassList = afterSchoolClassRepository.findAll();
        int count = afterSchoolClassList.size();
        for(int i = 0; i<count; i++){
            AfterSchoolClassManageForm afterSchoolClassManageForm = new AfterSchoolClassManageForm();
            afterSchoolClassManageForm.setId(afterSchoolClassList.get(i).getId());
            afterSchoolClassManageForm.setClass_name(afterSchoolClassList.get(i).getClass_name());
            afterSchoolClassManageForm.setStart_time(afterSchoolClassList.get(i).getStart_time().toLocalTime());
            afterSchoolClassManageForm.setEnd_time(afterSchoolClassList.get(i).getEnd_time().toLocalTime());
            Long day = afterSchoolClassList.get(i).getDay();
            if(day == 1L){
                afterSchoolClassManageForm.setDay("월");
            } else if (day == 2L){
                afterSchoolClassManageForm.setDay("화");
            } else if (day == 3L){
                afterSchoolClassManageForm.setDay("수");
            } else if (day == 4L){
                afterSchoolClassManageForm.setDay("목");
            } else if (day == 5L){
                afterSchoolClassManageForm.setDay("금");
            } else {
                System.out.println("Day doesn't exist");
                afterSchoolClassManageForm.setDay("error day");
            }
            afterSchoolClassManageFormList.add(afterSchoolClassManageForm);
        }
        return afterSchoolClassManageFormList;
    }

    public void addNewStudentScheduleManageForm(StudentScheduleManageForm studentScheduleManageForm){
        List<Student> isStudent = studentRepository.findByName(studentScheduleManageForm.getName());
        List<AfterSchoolClass> isAfterSchoolClass = afterSchoolClassRepository.findByClassName(studentScheduleManageForm.getClass_name());
        if((isStudent.size() == 1)&&(isAfterSchoolClass.size()==1)&&(isStudent.get(0).getDisable()==1)){
            List<StudentSchedule> result = studentScheduleRepository.findByStudent_idClass_id(isStudent.get(0).getId(),isAfterSchoolClass.get(0).getId());
            if(result.size()==1){
                System.out.println("This student schedule is already existing");
            }
            else if (result.size()==0) {
                List<StudentTime> isStudentTime = studentTimeRepository.findByStudent_id(isStudent.get(0).getId());
                if(isStudentTime.size()==0){
                    System.out.println("There is no entry and off time for student. Please add entry and off time first.");
                    return;
                }
                else if (isStudentTime.size()==1) {
                    switch (isAfterSchoolClass.get(0).getDay().intValue()){
                        case 1: if(isAfterSchoolClass.get(0).getStart_time().before(isStudentTime.get(0).getEntry_1()) ||
                        isAfterSchoolClass.get(0).getEnd_time().after(isStudentTime.get(0).getOff_1())){
                            System.out.println("Invalid afterschoolclass time in day1");
                            return;
                        } else break;

                        case 2: if(isAfterSchoolClass.get(0).getStart_time().before(isStudentTime.get(0).getEntry_2()) ||
                                isAfterSchoolClass.get(0).getEnd_time().after(isStudentTime.get(0).getOff_2())){
                            System.out.println("Invalid afterschoolclass time in day2");
                            return;
                        } else break;

                        case 3: if(isAfterSchoolClass.get(0).getStart_time().before(isStudentTime.get(0).getEntry_3()) ||
                                isAfterSchoolClass.get(0).getEnd_time().after(isStudentTime.get(0).getOff_3())){
                            System.out.println("Invalid afterschoolclass time in day3");
                            return;
                        } else break;

                        case 4: if(isAfterSchoolClass.get(0).getStart_time().before(isStudentTime.get(0).getEntry_4()) ||
                                isAfterSchoolClass.get(0).getEnd_time().after(isStudentTime.get(0).getOff_4())){
                            System.out.println("Invalid afterschoolclass time in day4");
                            return;
                        } else break;

                        case 5: if(isAfterSchoolClass.get(0).getStart_time().before(isStudentTime.get(0).getEntry_5()) ||
                                isAfterSchoolClass.get(0).getEnd_time().after(isStudentTime.get(0).getOff_5())){
                            System.out.println("Invalid afterschoolclass time in day5");
                            return;
                        } else break;

                        default: System.out.println("Invalid day");
                        return;
                    }
                    //Required after school class duplicate time check
                    System.out.println("Add new student schedule");
                    StudentSchedule studentSchedule = new StudentSchedule();
                    studentSchedule.setStudent_id(isStudent.get(0).getId());
                    studentSchedule.setClass_id(isAfterSchoolClass.get(0).getId());
                    studentScheduleRepository.save(studentSchedule);
                }
            } else {
                System.out.println("Duplicate student schedule in add NewStudentScheduleManageForm");
            }
        } else if(isStudent.size()==0){
            System.out.println("There is no that kinds of dolbom student in addNewStudentScheduleManageForm");
            return;
        } else if (isAfterSchoolClass.size()==0) {
            System.out.println("There is no that kinds of AfterSchoolClass in addNewStudentScheduleManageForm");
            return;
        } else if (isStudent.get(0).getDisable()==0) {
            System.out.println("There is no activation dolbom student in addNewStudentScheduleManageForm");
            return;
        } else {
            System.out.println("Duplicate dolbom student or dolbom afterschoolclass in addNewStudentScheduleManageForm");
        }
    }
    public void deleteStudentSchedule(Long id){
        Optional<StudentSchedule> result = studentScheduleRepository.findById(id);
        if(result.isPresent()){
            System.out.println("Delete StudentSchedule");
            studentScheduleRepository.deleteStudentSchedule(id);
        } else {
            System.out.println("Student schedule cannot be deleted because it does not exist");
        }
    }

    public List<StudentScheduleManageForm> sendStudentSchedule(){
        List<StudentSchedule> studentScheduleList = studentScheduleRepository.findAll();
        List<StudentScheduleManageForm> studentScheduleFormList = new ArrayList<>();
        int count = studentScheduleList.size();
        for(int i = 0; i < count; i++){
            StudentScheduleManageForm studentScheduleForm = new StudentScheduleManageForm();
            studentScheduleForm.setId(studentScheduleList.get(i).getId());
            studentScheduleForm.setName(studentRepository.findById(studentScheduleList.get(i).getStudent_id()).get().getName());
            studentScheduleForm.setClass_name(afterSchoolClassRepository.findById(studentScheduleList.get(i).getClass_id()).get().getClass_name());
            studentScheduleFormList.add(studentScheduleForm);
        }
        return studentScheduleFormList;
    }

    public void addNewStudentTime(StudentTime studentTime){
        List<StudentTime> result = studentTimeRepository.findByStudent_id(studentTime.getStudent_id());
        if(result.size() == 1){
            System.out.println("This student time is already existing by csv");
        } else if (result.size()==0) {
            System.out.println("Add new student time by csv");
            studentTimeRepository.save(studentTime);
        }
    }

    public void addNewStudentTimeManageForm(StudentTimeManageForm studentTimeManageForm){
        List<Student> isStudent = studentRepository.findByName(studentTimeManageForm.getName());
        if (isStudent.size()==1){
            List<StudentTime> result = studentTimeRepository.findByStudent_id(isStudent.get(0).getId());
            if(result.size()==1){
                System.out.println("This student time is already existing");
            } else if (result.size()==0) {
                System.out.println("Add new student time");
                StudentTime studentTime = new StudentTime();
                studentTime.setId(studentTimeManageForm.getId());
                studentTime.setStudent_id(isStudent.get(0).getId());
                if((studentTimeManageForm.getEntry_1().isAfter(studentTimeManageForm.getOff_1())) ||
                        studentTimeManageForm.getEntry_1().equals(studentTimeManageForm.getOff_1())){
                    System.out.println("entry time1 is equal or after than off time1");
                    return;
                }
                if((studentTimeManageForm.getEntry_2().isAfter(studentTimeManageForm.getOff_2())) ||
                        studentTimeManageForm.getEntry_2().equals(studentTimeManageForm.getOff_2())){
                    System.out.println("entry time2 is equal or after than off time2");
                    return;
                }
                if((studentTimeManageForm.getEntry_3().isAfter(studentTimeManageForm.getOff_3())) ||
                        studentTimeManageForm.getEntry_3().equals(studentTimeManageForm.getOff_3())){
                    System.out.println("entry time3 is equal or after than off time3");
                    return;
                }
                if((studentTimeManageForm.getEntry_4().isAfter(studentTimeManageForm.getOff_4())) ||
                        studentTimeManageForm.getEntry_4().equals(studentTimeManageForm.getOff_4())){
                    System.out.println("entry time4 is equal or after than off time4");
                    return;
                }
                if((studentTimeManageForm.getEntry_5().isAfter(studentTimeManageForm.getOff_5())) ||
                        studentTimeManageForm.getEntry_5().equals(studentTimeManageForm.getOff_5())){
                    System.out.println("entry time5 is equal or after than off time5");
                    return;
                }
                studentTime.setEntry_1(Time.valueOf(studentTimeManageForm.getEntry_1()));
                studentTime.setEntry_2(Time.valueOf(studentTimeManageForm.getEntry_2()));
                studentTime.setEntry_3(Time.valueOf(studentTimeManageForm.getEntry_3()));
                studentTime.setEntry_4(Time.valueOf(studentTimeManageForm.getEntry_4()));
                studentTime.setEntry_5(Time.valueOf(studentTimeManageForm.getEntry_5()));
                studentTime.setOff_1(Time.valueOf(studentTimeManageForm.getOff_1()));
                studentTime.setOff_2(Time.valueOf(studentTimeManageForm.getOff_2()));
                studentTime.setOff_3(Time.valueOf(studentTimeManageForm.getOff_3()));
                studentTime.setOff_4(Time.valueOf(studentTimeManageForm.getOff_4()));
                studentTime.setOff_5(Time.valueOf(studentTimeManageForm.getOff_5()));
                studentTimeRepository.save(studentTime);
            }
        } else if (isStudent.size()==0) {
            System.out.println("There is no that kinds of dolbom student");
            return;
        } else {
            System.out.println("Duplicate student in addNewStudentTimeManageForm");
            return;
        }
    }
    public void deleteStudentTime(Long id){
        Optional<StudentTime> result = studentTimeRepository.findById(id);
        if(result.isPresent()){
            System.out.println("Delete student time");
            studentTimeRepository.deleteStudentTime(id);
        } else {
            System.out.println("Student time cannot be deleted because it does not exist");
        }
    }
    public List<StudentTimeManageForm> sendStudentTimeManageFormList(){
        List<StudentTime> studentTimeList = studentTimeRepository.findAll();
        List<StudentTimeManageForm> studentTimeManageFormList = new ArrayList<>();
        int count = studentTimeList.size();
        for(int i = 0; i < count; i++){
            StudentTimeManageForm studentTimeManageForm = new StudentTimeManageForm();
            studentTimeManageForm.setId(studentTimeList.get(i).getId());
            studentTimeManageForm.setName(studentRepository.findById(studentTimeList.get(i).getStudent_id()).get().getName());
            studentTimeManageForm.setEntry_1(studentTimeList.get(i).getEntry_1().toLocalTime());
            studentTimeManageForm.setEntry_2(studentTimeList.get(i).getEntry_2().toLocalTime());
            studentTimeManageForm.setEntry_3(studentTimeList.get(i).getEntry_3().toLocalTime());
            studentTimeManageForm.setEntry_4(studentTimeList.get(i).getEntry_4().toLocalTime());
            studentTimeManageForm.setEntry_5(studentTimeList.get(i).getEntry_5().toLocalTime());
            studentTimeManageForm.setOff_1(studentTimeList.get(i).getOff_1().toLocalTime());
            studentTimeManageForm.setOff_2(studentTimeList.get(i).getOff_4().toLocalTime());
            studentTimeManageForm.setOff_3(studentTimeList.get(i).getOff_3().toLocalTime());
            studentTimeManageForm.setOff_4(studentTimeList.get(i).getOff_4().toLocalTime());
            studentTimeManageForm.setOff_5(studentTimeList.get(i).getOff_5().toLocalTime());
            studentTimeManageFormList.add(studentTimeManageForm);
        }
        return studentTimeManageFormList;
    }
}
