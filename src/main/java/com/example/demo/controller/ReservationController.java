package com.example.demo.controller;

import com.example.demo.model.request.DeleteReservationRequest;
import com.example.demo.model.request.NewReservationRequest;
import com.example.demo.model.request.ReservationGetRequest;
import com.example.demo.model.request.UpdateReservationRequest;
import com.example.demo.manager.ResarvationManager;
import com.example.demo.model.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@RequestMapping(path="api/reservation")
public class ReservationController {


    private  final ResarvationManager resarvationManager;

    @Autowired
    public ReservationController( ResarvationManager resarvationManager) {

        this.resarvationManager = resarvationManager;
    }

    @GetMapping
    public List<Reservation> getReservations(){
         return resarvationManager.getReservations();
    }

    @GetMapping(path="{userName}")
    public List<Reservation> getReservationsByUser(@PathVariable("userName") String userName){
        ReservationGetRequest reservationGetRequest = new ReservationGetRequest();
        reservationGetRequest.setUserName(userName);
        return resarvationManager.getReservationsByUser(reservationGetRequest);
    }

    @PostMapping
    public void registerNewReservation(@RequestBody NewReservationRequest reservationRequest){
        resarvationManager.initializeReservation(reservationRequest);
    }

    @DeleteMapping(path="{reservationId}")
    public void deletingReservation(@PathVariable("reservationId") Long reservationId ){
        resarvationManager.removeReservation(reservationId);
    }


    @PutMapping(path = "/update")
    public void updatingReservation(@RequestBody UpdateReservationRequest updateReservationRequest){
        resarvationManager.updateReservation(updateReservationRequest);
    }
}
