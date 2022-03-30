package com.example.restfull_task1.payload;

import com.example.restfull_task1.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDTO {

    private String corpName;

    private String directorName;

    private Integer addressId;
}
