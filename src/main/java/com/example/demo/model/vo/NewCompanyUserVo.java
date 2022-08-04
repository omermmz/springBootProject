package com.example.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCompanyUserVo {
    private String companyStatus;
    private String companyName;
    private String userStatus;
    private String type;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Long telNo;
    private String email;
    private String password;
}
