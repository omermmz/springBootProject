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
public class NewReservationUserRequest {
    private String status;
    private String type;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private Long phoneNumber;
    private String email;
    private String password;
}
