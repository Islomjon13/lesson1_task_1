package com.example.restfull_task1.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String corpName;

    private String directorName;

    @OneToOne  // bitta joyda bitta company bo'ladi
    private Address address;
}
