package com.example.demo.manager;

import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.model.dto.ReservationTimeDTO;
import com.example.demo.model.entity.Reservation;
import com.example.demo.model.request.*;
import com.example.demo.model.vo.*;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResarvationManager {

    private final ReservationService reservationService;

    public void initializeReservation(NewReservationRequest newReservationRequest){
        NewReservationVo newReservationVo = new NewReservationVo();
        newReservationVo.setDate(newReservationRequest.getDate());
        newReservationVo.setTime(newReservationRequest.getTime());
        newReservationVo.setPlaceId(newReservationRequest.getPlaceId());
        newReservationVo.setUserId(newReservationRequest.getUserId());

        reservationService.addNewReservation(newReservationVo);
    }

    public void removeReservation(Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    public void  updateReservation(UpdateReservationRequest updateReservationRequest) {
        UpdateReservationVo updateReservationVo = new UpdateReservationVo();
        updateReservationVo.setId(updateReservationRequest.getId());
        updateReservationVo.setDate(updateReservationRequest.getDate());
        updateReservationVo.setTime(updateReservationRequest.getTime());
        updateReservationVo.setPlaceId(updateReservationRequest.getPlaceId());
        updateReservationVo.setUserId(updateReservationRequest.getUserId());

        reservationService.updateNewReservation(updateReservationVo);
    }

    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    public List<Reservation> getReservationsByUser(ReservationGetRequest reservationGetRequest) {
        ReservationGetVo reservationGetVo = new ReservationGetVo();
        reservationGetVo.setUserName(reservationGetRequest.getUserName());

        return reservationService.getReservationsByUser(reservationGetVo);

    }

    public List<ReservationTimeDTO> getReservationsAllEmptyTime(GetEmptyTimeRequest getEmptyTimeRequest) {
        GetEmptyTimeVo getEmptyTimeVo = convert(getEmptyTimeRequest);
        return reservationService.getReservationsAllEmptyTime(getEmptyTimeVo);
    }

    private GetEmptyTimeVo convert(GetEmptyTimeRequest getEmptyTimeRequest){
        GetEmptyTimeVo getEmptyTimeVo = new GetEmptyTimeVo();
        getEmptyTimeVo.setPlaceId(getEmptyTimeRequest.getPlaceId());
        getEmptyTimeVo.setDate(getEmptyTimeRequest.getDate());
        getEmptyTimeVo.setStatus(getEmptyTimeRequest.getStatus());
        return getEmptyTimeVo;
    }

    public List<ReservationTimeDTO> getReservationsAllEmptySpecialTime(GetEmptySpecialTimeRequest getEmptySpecialTimeRequest) {
        GetEmptySpecialTimeVo getEmptySpecialTimeVo = convert(getEmptySpecialTimeRequest);
        return reservationService.getReservationsAllEmptySpecialTime(getEmptySpecialTimeVo);
    }
    private GetEmptySpecialTimeVo convert(GetEmptySpecialTimeRequest getEmptySpecialTimeRequest){
        GetEmptySpecialTimeVo getEmptySpecialTimeVo = new GetEmptySpecialTimeVo();
        getEmptySpecialTimeVo.setPlaceId(getEmptySpecialTimeRequest.getPlaceId());
        getEmptySpecialTimeVo.setDate(getEmptySpecialTimeRequest.getDate());
        getEmptySpecialTimeVo.setStatus(getEmptySpecialTimeRequest.getStatus());
        getEmptySpecialTimeVo.setTimeInterval(getEmptySpecialTimeRequest.getTimeInterval());
        return getEmptySpecialTimeVo;
    }

    public ArrayList<String> fillTimeList(String s, String s1) {
        return reservationService.fillTimeList(s,s1);
    }

    public List<ReservationDTO> getMyReservations(Long userId) {
        return reservationService.getMyReservations(userId);
    }

    public List<ReservationDTO> getPLaceReservations(ShowPlaceReservationRequest showPlaceReservationRequest) {
        return reservationService.getPlaceReservations(showPlaceReservationRequest.getPlaceFieldId());
    }
}
