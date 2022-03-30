package com.example.restfull_task1.service;

import com.example.restfull_task1.entity.Company;
import com.example.restfull_task1.entity.Department;
import com.example.restfull_task1.payload.ApiResponse;
import com.example.restfull_task1.payload.DepartmentDTO;
import com.example.restfull_task1.repository.CompanyRepository;
import com.example.restfull_task1.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    final DepartmentRepository departmentRepository;
    final CompanyRepository companyRepository;

    public ApiResponse all() {
        List<Department> all = departmentRepository.findAll();
        if (all.isEmpty()) {
            return new ApiResponse("no departments",false);
        }
        return new ApiResponse("all",true,all);
    }

    public ApiResponse one(Integer id) {
        if (departmentRepository.existsById(id)) {
            return new ApiResponse("found",true,departmentRepository.getById(id));
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse delete(Integer id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }
        return new ApiResponse("not found",false);
    }

    public ApiResponse add( DepartmentDTO dto) {
        // checking company has or not ?
        if (companyRepository.existsById(dto.getCompanyId())) {
            Company company = companyRepository.getById(dto.getCompanyId());

            // before adding name is unique ?
            if (!departmentRepository.existsByName(dto.getName())){
                Department department = new Department();
                department.setName(dto.getName());
                department.setCompany(company);

                Department save = departmentRepository.save(department);
                return new ApiResponse("added",true,save);
            }
        }
        return new ApiResponse("something went wrong",false);
    }

    public ApiResponse edit(Integer id, DepartmentDTO dto) {
        // exists or not :
        if (departmentRepository.existsById(id)) {
            Department department = departmentRepository.getById(id);
            // checking company has or not ?
            if (companyRepository.existsById(dto.getCompanyId())) {
                Company company = companyRepository.getById(dto.getCompanyId());

                // before adding name is unique ? and not in this id
                if (!departmentRepository.existsByNameAndIdNot(dto.getName(), dto.getCompanyId())){

                    department.setName(dto.getName());
                    department.setCompany(company);

                    Department save = departmentRepository.save(department);
                    return new ApiResponse("added",true,save);
                }
            }
        }
            return new ApiResponse("something went wrong",false);
    }
}
