package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.Cookie;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WhoAmIDTO {
    private Long userId;
    private Long companyId;
    private String userName;
    private String userSurname;
    private String role;
    private String userType;
}
