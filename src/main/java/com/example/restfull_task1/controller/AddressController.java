package com.example.restfull_task1.controller;

import com.example.restfull_task1.payload.AddressDTO;
import com.example.restfull_task1.payload.ApiResponse;
import com.example.restfull_task1.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
@RequiredArgsConstructor
public class AddressController {
    final AddressService addressService;

    @GetMapping
    public ResponseEntity all() {
        ApiResponse apiResponse = addressService.all();
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    /**
     * id orqali bittasini olamiz
     */
    @GetMapping("/{id}")
    public ResponseEntity one(@PathVariable Integer id) {
        ApiResponse apiResponse = addressService.one(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * delete qilish id orqali
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        ApiResponse apiResponse = addressService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    /**
     * post qilish payload dan berilgan holda validation qilingan
     */
    @Validated
    @PostMapping
    public ResponseEntity add( @RequestBody AddressDTO dto){
        ApiResponse apiResponse = addressService.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Integer id,@RequestBody AddressDTO dto){
        ApiResponse apiResponse = addressService.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess() ?
                HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
