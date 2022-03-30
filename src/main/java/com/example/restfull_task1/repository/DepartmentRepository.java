package com.example.restfull_task1.repository;

import com.example.restfull_task1.entity.Address;
import com.example.restfull_task1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
