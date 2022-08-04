package com.example.demo.controller;

import com.example.demo.manager.ReservationUserManager;
import com.example.demo.model.request.NewReservationUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/reservationuser")
@RequiredArgsConstructor
public class ReservationUserController {
    private final ReservationUserManager reservationUserManager;

    @PostMapping
    public void registerNewReservationUser(@RequestBody NewReservationUserRequest newReservationUserRequest){
            reservationUserManager.initializeReservUser(newReservationUserRequest);
    }
}
