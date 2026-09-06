package com.employeemanagement.ems;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Welcome to the Employee Managment System*";
    }

    @GetMapping("/status")
    public String getStatus(){
        return "Server is running perfectly!";
    }


}
