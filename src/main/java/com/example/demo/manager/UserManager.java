package com.example.demo.manager;

import com.example.demo.model.dto.CompanyUserIdDTO;
import com.example.demo.model.dto.ReservationUserIdDTO;
import com.example.demo.service.CompanyUserService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserManager {
    private final UserService userService;

    public List<CompanyUserIdDTO> getCompanyUsersByType(String type) {
        return userService.getCompanyUsersByType(type);
    }

    public List<ReservationUserIdDTO> getReservationUsersByType(String type) {
        return userService.getReservationUsersByType(type);
    }
}
