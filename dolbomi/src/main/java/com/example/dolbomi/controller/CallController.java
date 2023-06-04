package com.example.dolbomi.controller;

import com.example.dolbomi.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@CrossOrigin
@RestController
public class CallController {
    private CallService callService;

    @Autowired
    public CallController(CallService callService){
        this.callService = callService;
    }

    @PostMapping("/callList/{parent_id}")
    public CallForm sendPhoneNum(@PathVariable("parent_id") Long parent_id){
        return callService.sendPhoneNum(parent_id);
    }
}
