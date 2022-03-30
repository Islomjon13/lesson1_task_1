package com.example.restfull_task1.service;

import com.example.restfull_task1.entity.Address;
import com.example.restfull_task1.payload.AddressDTO;
import com.example.restfull_task1.payload.ApiResponse;
import com.example.restfull_task1.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    final AddressRepository addressRepository;

    public ApiResponse all() {
        List<Address> all = addressRepository.findAll();
        return new ApiResponse("all", true, all);
    }

    public ApiResponse one(Integer id) {
        if (addressRepository.existsById(id)) {
            Address address = addressRepository.getById(id);
            return new ApiResponse("found", true, address);
        }
        return new ApiResponse("not found", false);
    }

    public ApiResponse delete(Integer id) {
        if (addressRepository.existsById(id)) {
            // deleting
            addressRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found", false);

    }

    public ApiResponse add(@NotNull AddressDTO dto) {
        if (addressRepository.existsByHomeNumberAndStreet(dto.getHomeNumber(), dto.getStreet())) {
            return new ApiResponse("already exists", false);
        }
        Address address = new Address();
        address.setHomeNumber(dto.getHomeNumber());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);
        return new ApiResponse("saved", true, save);
    }

    public ApiResponse edit(Integer id, AddressDTO dto) {
        if (addressRepository.existsById(id)) {
            Address address = addressRepository.getById(id);
            if (addressRepository.existsByHomeNumberAndStreet(dto.getHomeNumber(), dto.getStreet())) {
                return new ApiResponse("already exists", false);
            }

            address.setHomeNumber(dto.getHomeNumber());
            address.setStreet(dto.getStreet());
            Address save = addressRepository.save(address);
            return new ApiResponse("saved", true, save);
        }
        return new ApiResponse("not found",false);
    }

}
