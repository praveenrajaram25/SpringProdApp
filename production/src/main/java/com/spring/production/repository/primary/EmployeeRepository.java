package com.spring.production.repository.primary;

import com.spring.production.model.primary.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Number> {
}
