package com.mysql.employees.controller;


import com.mysql.employees.entity.Employee;
import com.mysql.employees.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee request) throws RuntimeException {
        return new ResponseEntity<>(employeeService.createEmployee(request), HttpStatus.OK);
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<Page<Employee>> findAllEmployees(@RequestParam("page") int page,
                                                           @RequestParam("size") int size) throws RuntimeException {
        return new ResponseEntity<>(employeeService.findAll(page, size), HttpStatus.OK);
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<Employee> findById(@RequestParam Integer id) throws RuntimeException {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteById")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam long id) throws RuntimeException {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}