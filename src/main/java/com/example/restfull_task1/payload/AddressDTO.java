package com.example.restfull_task1.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AddressDTO {


    private String street;

    private Integer homeNumber;

}
