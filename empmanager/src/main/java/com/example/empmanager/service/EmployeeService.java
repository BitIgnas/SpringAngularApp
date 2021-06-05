package com.example.empmanager.service;

import com.example.empmanager.exception.UserNotFoundException;
import com.example.empmanager.model.Employee;
import com.example.empmanager.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        employeeRepository.save(employee);
        log.info("[Employee was added]");
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
        log.info("[Employee was updated]");
    }

    public Employee findEmployeeById(Long id) throws UserNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User by id" + id + " was not found"));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
