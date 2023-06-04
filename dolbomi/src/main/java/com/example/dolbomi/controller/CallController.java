package com.example.dolbomi.controller;

import com.example.dolbomi.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallController {
    private CallService callService;

    @Autowired
    public CallController(CallService callService){
        this.callService = callService;
    }
}
