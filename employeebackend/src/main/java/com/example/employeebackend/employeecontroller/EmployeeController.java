package com.example.employeebackend.employeecontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.employeebackend.employeemodel.employee;
import com.example.employeebackend.employeerepository.EmployeeRepository;
import com.example.employeebackend.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository emprepo;
    //get all employees
    @GetMapping("/employees")
    public List<employee> getAllEmployees(){
        return emprepo.findAll();
    }
    
    //create employee rest api
    @PostMapping("/employees")
    public employee createEmployee(@RequestBody employee Employee){
        return emprepo.save(Employee);
    }
    //get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<employee> getEmployeeById(@PathVariable Long id){
        employee Employee=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id: "+id));
        return ResponseEntity.ok(Employee);

    }
    //update employee rest api
    @PutMapping("/employees/{id}")
    public ResponseEntity<employee> updateEmployee(@PathVariable Long id, @RequestBody employee employeeDetails){
        employee Employee=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id: "+id));
        Employee.setFirstName(employeeDetails.getFirstName());
        Employee.setLastName(employeeDetails.getLastName());
        Employee.setEmailId(employeeDetails.getEmailId());
        employee updatedEmployee=emprepo.save(Employee);
        return ResponseEntity.ok(updatedEmployee);
        

    }
    //delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        employee Employee=emprepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist with id: "+id));
        emprepo.delete(Employee);
        Map<String,Boolean> response =new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

