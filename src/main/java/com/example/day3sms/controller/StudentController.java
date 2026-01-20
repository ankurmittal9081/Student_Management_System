package com.example.day3sms.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class StudentController {


    //controller -> request
    //Service -> Logic
    // Repository -> database
    // Model -> data Schema

    @GetMapping("/")
    public String hello(){
        return "Homepage for student";
    }

}
