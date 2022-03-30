package com.example.restfull_task1.controller;

import com.example.restfull_task1.payload.ApiResponse;
import com.example.restfull_task1.payload.CompanyDTO;
import com.example.restfull_task1.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    final CompanyService companyService;

    /**
     * get all from db
     */
    @GetMapping
    public ResponseEntity all(){
        ApiResponse apiResponse = companyService.all();
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * get one from db using id
     */
    @GetMapping("/{id}")
    public ResponseEntity one(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.one(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * deleting from db
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * add to db using dto
     */
    @PostMapping
    public ResponseEntity add(@RequestBody CompanyDTO dto){
        ApiResponse apiResponse = companyService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * editing data
     */
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id,@RequestBody CompanyDTO dto){
        ApiResponse apiResponse = companyService.edit(dto,id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
