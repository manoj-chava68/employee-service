package com.employeedemo.employee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spring-boot")
public class Controller {
    @GetMapping
    public String Hello(){
        return "Hello World";
    }

}