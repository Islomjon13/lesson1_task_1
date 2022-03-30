package com.example.restfull_task1.service;

import com.example.restfull_task1.entity.Address;
import com.example.restfull_task1.entity.Company;
import com.example.restfull_task1.payload.ApiResponse;
import com.example.restfull_task1.payload.CompanyDTO;
import com.example.restfull_task1.repository.AddressRepository;
import com.example.restfull_task1.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    final CompanyRepository companyRepository;
    final AddressRepository addressRepository;

    public ApiResponse all() {
        List<Company> all = companyRepository.findAll();

        return new ApiResponse("all",true,all);
    }

    public ApiResponse one(Integer id) {
        if (companyRepository.existsById(id)) {
            return new ApiResponse("found",true,companyRepository.getById(id));
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse delete(Integer id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse add(CompanyDTO dto) {
        if (!companyRepository.existsByCorpName(dto.getCorpName())){
            if (addressRepository.existsById(dto.getAddressId())){
                Address address = addressRepository.getById(dto.getAddressId());
                Company company = new Company();

                company.setAddress(address);
                company.setCorpName(dto.getCorpName());
                company.setDirectorName(dto.getDirectorName());

                Company save = companyRepository.save(company);

                return new ApiResponse("added",true,save);
            }
        }
        return new ApiResponse("already exists",false);
    }

    public ApiResponse edit(CompanyDTO dto, Integer id) {
        if (companyRepository.existsById(id)) {
            Company company = companyRepository.getById(id);
            if (!companyRepository.existsByCorpName(dto.getCorpName())){
                if (addressRepository.existsById(dto.getAddressId())){
                    Address address = addressRepository.getById(dto.getAddressId());

                    company.setAddress(address);
                    company.setCorpName(dto.getCorpName());
                    company.setDirectorName(dto.getDirectorName());

                    Company save = companyRepository.save(company);

                    return new ApiResponse("edited",true,save);
                }
            }
        }
        return new ApiResponse("not found",false);
    }
}
