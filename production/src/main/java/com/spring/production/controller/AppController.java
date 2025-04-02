package com.spring.production.controller;


import com.spring.production.model.EmployeeData;
import com.spring.production.service.SpringService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="/entry")
public class AppController {

    private static final Logger logger = LogManager.getLogger(AppController.class);

    @Autowired
    private SpringService springService;


    @GetMapping("/api1")
    public String getData(){
        return "Hello";
    }

    @GetMapping("/features/test")
    public String testDataBaseConnection(){


        return springService.testDB();
    }


    @PostMapping("/employee")
    public  String addEmployee(@RequestBody EmployeeData emp){
        return springService.addEmployee(emp);

    }



}
