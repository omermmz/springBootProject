package com.example.demo.controller;

import com.example.demo.manager.ReservationUserManager;
import com.example.demo.model.dto.CompanyUserIdDTO;
import com.example.demo.model.dto.ReservationUserIdDTO;
import com.example.demo.model.request.NewReservationUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/reservationuser")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
public class ReservationUserController {
    private final ReservationUserManager reservationUserManager;



    @PostMapping
    public void registerNewReservationUser(@RequestBody NewReservationUserRequest newReservationUserRequest){
            reservationUserManager.initializeReservUser(newReservationUserRequest);
    }
}
