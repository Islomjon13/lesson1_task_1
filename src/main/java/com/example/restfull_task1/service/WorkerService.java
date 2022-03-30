package com.example.restfull_task1.service;

import com.example.restfull_task1.entity.Worker;
import com.example.restfull_task1.payload.ApiResponse;
import com.example.restfull_task1.payload.WorkerDTO;
import com.example.restfull_task1.repository.AddressRepository;
import com.example.restfull_task1.repository.DepartmentRepository;
import com.example.restfull_task1.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerService {
    final DepartmentRepository departmentRepository;
    final WorkerRepository workerRepository;
    final AddressRepository addressRepository;

    public ApiResponse all() {
        return new ApiResponse("all", true, workerRepository.findAll());
    }

    public ApiResponse one(Integer id) {
        if (workerRepository.existsById(id)) {
            return new ApiResponse("found", true, workerRepository.getById(id));
        }
        return new ApiResponse("not found", false);
    }

    public ApiResponse delete(Integer id) {
        if (workerRepository.existsById(id)) {
            workerRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }
        return new ApiResponse("not found", false);
    }

    public ApiResponse add(WorkerDTO dto) {
        if (!workerRepository.existsByPhoneNumber(dto.getPhoneNumber())) {

            if (addressRepository.existsById(dto.getAddressId())) {

                if (departmentRepository.existsById(dto.getAddressId())) {

                    Worker worker = new Worker();

                    worker.setAddress(addressRepository.getById(dto.getAddressId()));
                    worker.setDepartment(departmentRepository.getById(dto.getAddressId()));
                    worker.setName(dto.getName());

                    Worker save = workerRepository.save(worker);

                    return new ApiResponse("added", true, save);
                }
            }

        }
        return new ApiResponse("Something went wrong", false);
    }

    public ApiResponse edit(Integer id, @NotNull WorkerDTO dto) {
        if (workerRepository.existsById(id)) {
            Worker worker = workerRepository.getById(id);
            if (!workerRepository.existsByPhoneNumber(dto.getPhoneNumber())) {

                if (addressRepository.existsById(dto.getAddressId())) {

                    if (departmentRepository.existsById(dto.getAddressId())) {

                        worker.setAddress(addressRepository.getById(dto.getAddressId()));
                        worker.setDepartment(departmentRepository.getById(dto.getAddressId()));
                        worker.setName(dto.getName());

                        Worker save = workerRepository.save(worker);

                        return new ApiResponse("edited", true, save);
                    }
                }
            }
        }
        return new ApiResponse("Something went wrong", false);
    }
}
