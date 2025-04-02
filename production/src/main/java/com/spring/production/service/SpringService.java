package com.spring.production.service;

import com.spring.production.model.EmployeeData;

public interface SpringService {
    String testDB();

    String addEmployee(EmployeeData emp);
}
