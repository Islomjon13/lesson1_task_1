package com.example.restfull_task1.payload;

import com.example.restfull_task1.entity.Address;
import com.example.restfull_task1.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.StringTokenizer;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private Integer addressId;
    @NotEmpty
    private Integer departmentId;

}
