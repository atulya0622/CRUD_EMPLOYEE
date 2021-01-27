package com.example.employeebackend.employeerepository;

import com.example.employeebackend.employeemodel.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepository extends JpaRepository<employee, Long> {
    
}
