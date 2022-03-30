package com.example.restfull_task1.controller;

import com.example.restfull_task1.payload.ApiResponse;
import com.example.restfull_task1.payload.DepartmentDTO;
import com.example.restfull_task1.repository.DepartmentRepository;
import com.example.restfull_task1.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    final DepartmentRepository departmentRepository;
    final DepartmentService departmentService;

    /**
     * get all
     */
    @GetMapping
    public ResponseEntity<?> all() {
        ApiResponse apiResponse = departmentService.all();
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    /**
     * get one using id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.one(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    /**
     * delete from db one department
     */
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse apiResponse = departmentService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    /**
     * adding to the db
     */
    @PostMapping
    public ResponseEntity<?> add(@RequestBody DepartmentDTO dto) {
        ApiResponse apiResponse = departmentService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    /**
     * editing data
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id, @RequestBody DepartmentDTO dto) {
        ApiResponse apiResponse = departmentService.edit(id, dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }


}
