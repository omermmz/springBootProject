package com.example.demo.model.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyUserIdDTO {
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String companyName;
    private Long userId;
    private String type;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Long telNo;
    private String email;
    private String password;
    private String roles;
}
