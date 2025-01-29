package com.mysql.employees.service;

import com.mysql.employees.entity.Employee;
import com.mysql.employees.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private static final String REDIS_CACHE_VALUE = "employee";

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> findAll(int page, int size) {
        PageRequest pageReq
                = PageRequest.of(page, size);
        return employeeRepository.findAll(pageReq);
    }

    @Cacheable(value = REDIS_CACHE_VALUE, key = "#id")
    public Employee findById(long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Transactional
    @CachePut(value = REDIS_CACHE_VALUE, key = "#employee.empID")
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    @CacheEvict(value = REDIS_CACHE_VALUE, key = "#id")
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }
}
