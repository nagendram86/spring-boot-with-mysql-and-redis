package com.mysql.employees.controller;

import com.mysql.employees.entity.Department;
import com.mysql.employees.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/findAll")
    public ResponseEntity<List<Department>> findAll() {
        return new ResponseEntity<>(departmentService.fetchAllDepartments(), HttpStatus.OK);
    }

    @GetMapping(value = "/findById")
    public ResponseEntity<Department> findById(@RequestParam String id) {
        return new ResponseEntity<>(departmentService.findByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Department> saveDepartment(Department department) {
        return new ResponseEntity<>(departmentService.createDepartment(department), HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteById")
    public ResponseEntity<HttpStatus> deleteDepartment(String id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
