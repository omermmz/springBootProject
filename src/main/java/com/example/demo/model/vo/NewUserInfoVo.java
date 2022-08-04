package com.example.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewUserInfoVo {
    private Long userId;
    private String type;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Long telNo;
}
