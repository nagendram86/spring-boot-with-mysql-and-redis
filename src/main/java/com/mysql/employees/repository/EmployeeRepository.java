package com.mysql.employees.repository;

import com.mysql.employees.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "employees", path = "employees")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}