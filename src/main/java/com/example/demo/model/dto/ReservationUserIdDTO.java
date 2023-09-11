package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationUserIdDTO {
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long userId;
    private String type;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Long telNo;
    private String email;
    private String password;
}
