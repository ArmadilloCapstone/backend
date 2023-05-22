package com.example.dolbomi.controller;


import com.example.dolbomi.domain.Student;
import com.example.dolbomi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
public class HelloWorldController {
    private final StudentService studentService;

    @Autowired
    public HelloWorldController(StudentService studentService){
        this.studentService = studentService;
    }
    @GetMapping("/hello")
    public List<String> hello(){
        return Arrays.asList("안녕하세요","hello","good","happy");
    }

    @GetMapping("/urlTest")
    @ResponseBody
    public void helloAbc(@RequestParam("id") Integer id, @RequestParam("title") String title){
        System.out.println("urlTest : " + id);
        System.out.println("urlTest : " + title);
    }

    @GetMapping("/urlTest2")
    @ResponseBody
    public void helloCBA(@RequestParam("clientId") Long clientId,@RequestParam("id") Long id, @RequestParam("name") String name,
                         @RequestParam("grade") Long grade, @RequestParam Long gender){
        StudentPickupForm studentPickupForm = new StudentPickupForm();
        studentPickupForm.setId(id);
        studentPickupForm.setName(name);
        studentPickupForm.setGrade(grade);
        studentPickupForm.setGender(gender);
        System.out.println(studentPickupForm.getId());
        System.out.println(studentPickupForm.getName());
        System.out.println(studentPickupForm.getGrade());
        System.out.println(studentPickupForm.getGender());
        System.out.println("clientId : " + clientId);
    }

    @GetMapping("/sendGet")
    public String sendGetTest(@RequestParam("num") Integer num){
        Integer inNum = num;
        return inNum.toString() + "결과";
    }


    @PostMapping("/postTest")
    public String postTest(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getName());
        System.out.println(student.getGrade());
        System.out.println(student.getPhone_num());
        return student.getName() + " : " + student.getPhone_num();
    }

//    @GetMapping("/hello")
//    public List<Student> hello(){
//        return studentService.findMembers();
//    }

}
