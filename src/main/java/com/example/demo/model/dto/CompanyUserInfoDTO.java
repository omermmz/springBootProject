package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUserInfoDTO {
    private Long id;
    private Long userId;
    private String type;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Long phoneNumber;
}
