package com.example.restfull_task1.repository;

import com.example.restfull_task1.entity.Address;
import com.example.restfull_task1.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpName(String name);
}
