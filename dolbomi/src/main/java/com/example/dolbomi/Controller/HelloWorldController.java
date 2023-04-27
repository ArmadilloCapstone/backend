package com.example.dolbomi.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloWorldController {
    @GetMapping("/")
    public String test(){
        return "index.html";
    }
}
