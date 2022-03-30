package com.example.restfull_task1.controller;

import com.example.restfull_task1.payload.ApiResponse;
import com.example.restfull_task1.payload.WorkerDTO;
import com.example.restfull_task1.service.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/worker")
@RequiredArgsConstructor
public class WorkerController {

    final WorkerService workerService;

    /**
     * get all
     */
    @GetMapping
    public ResponseEntity<?> all() {
        ApiResponse apiResponse = workerService.all();
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    /**
     * get one from db using id
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Integer id) {
        ApiResponse apiResponse = workerService.one(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    /**
     * deleting using id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    /**
     * adding to the workers
     */
    @Validated
    @PostMapping
    public ResponseEntity<?> add(@RequestBody WorkerDTO dto){
        ApiResponse apiResponse = workerService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * editing using WorkerDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Integer id,@RequestBody WorkerDTO dto){
        ApiResponse apiResponse = workerService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
