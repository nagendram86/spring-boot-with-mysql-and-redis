package com.mysql.employees.service;

import com.mysql.employees.entity.Department;
import com.mysql.employees.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private static final String REDIS_CACHE_VALUE = "department";

    @Autowired
    DepartmentRepository departmentRepository;

    @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
    public Department findByID(String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no department found"));
    }

    @Transactional
    @CachePut(value = REDIS_CACHE_VALUE, key = "#department.deptNo")
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Transactional
    @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id")
    public void deleteDepartment(String id) {
        departmentRepository.deleteById(id);
    }

    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }
}
