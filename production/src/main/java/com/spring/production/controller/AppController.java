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

    
    //Testing all the Spring Boot Features such as Env based DB Connections,Logging,PropertyFile loading.
    @GetMapping("/features/test")
    public String testDataBaseConnection(){
        return springService.testDB();
    }


    //Make a DB insertion into both primary and seconday DB systems to check DB Management
    @PostMapping("/employee")
    public  String addEmployee(@RequestBody EmployeeData emp){
        return springService.addEmployee(emp);

    }



}
