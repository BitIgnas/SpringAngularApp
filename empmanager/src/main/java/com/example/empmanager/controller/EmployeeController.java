package com.example.empmanager.controller;

import com.example.empmanager.exception.UserNotFoundException;
import com.example.empmanager.model.Employee;
import com.example.empmanager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws UserNotFoundException {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
