package com.example.demo.controller;

import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.model.dto.ReservationTimeDTO;
import com.example.demo.model.request.*;
import com.example.demo.manager.ResarvationManager;
import com.example.demo.model.entity.Reservation;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
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
    @PostMapping(path = "/getemptytime")
    public List<ReservationTimeDTO> getReservationsAllEmptyTime(@RequestBody GetEmptyTimeRequest getEmptyTimeRequest){
        return resarvationManager.getReservationsAllEmptyTime(getEmptyTimeRequest);
    }

    @GetMapping(path = "/getemptyspecialtime")
    public List<ReservationTimeDTO> getReservationsAllEmptySpecialTime(@RequestBody GetEmptySpecialTimeRequest getEmptySpecialTimeRequest){
        return resarvationManager.getReservationsAllEmptySpecialTime(getEmptySpecialTimeRequest);
    }

    @GetMapping(path = "/myReservations/{userId}")
    public List<ReservationDTO> getMyReservations(@PathVariable("userId") Long userId){
        return resarvationManager.getMyReservations(userId);
    }
    @PostMapping(path = "/getPlaceReservations")
    public List<ReservationDTO> getPlaceReservations(@RequestBody ShowPlaceReservationRequest showPlaceReservationRequest){
        return resarvationManager.getPLaceReservations(showPlaceReservationRequest);
    }

    @GetMapping(path = "/timeicindeneme")
    public ArrayList<String> timeicindeneme(){
        return resarvationManager.fillTimeList("09:00:00","23:00:00");
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
