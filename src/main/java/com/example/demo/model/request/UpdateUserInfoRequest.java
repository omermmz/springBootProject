package com.example.demo.model.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoRequest {
    private String companyName;
    private String userName;
    private String userSurname;
    private LocalDate birthdate;
    private Long phoneNumber;
}
