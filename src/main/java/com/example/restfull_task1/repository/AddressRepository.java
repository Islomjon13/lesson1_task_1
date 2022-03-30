package com.example.restfull_task1.repository;

import com.example.restfull_task1.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
boolean existsByHomeNumberAndStreet(Integer homeNumber,String street);

}
