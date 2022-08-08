package com.example.demo.service;


import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.model.dto.ReservationTimeDTO;
import com.example.demo.model.entity.Reservation;
import com.example.demo.model.vo.*;
import com.example.demo.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PlaceInfoService placeInfoService;



    public List<Reservation> getReservations() {
        return reservationRepository.findAll();

    }

    public ReservationDTO addNewReservation(NewReservationVo newReservationVo) {
        checkReservationExistsOnGivenDate(newReservationVo.getDate(),newReservationVo.getTime(),newReservationVo.getPlaceId());
        Reservation reservation = convert(newReservationVo);
        reservation = reservationRepository.save(reservation);
        return convert(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Transactional
    public void updateNewReservation(UpdateReservationVo updateReservationVo) {
        Reservation reservation1 = checkReservationFindById(updateReservationVo.getId());
        updateReservation(reservation1, updateReservationVo);
    }

    private void checkReservationExistsOnGivenDate(LocalDate date, LocalTime time,Long placeId) {
         reservationRepository.findReservationByDateAndTimeAndPlaceId(date,time,placeId)
                .ifPresent(reservation -> {
                    throw new IllegalStateException("Reservation taken");
                });
    }

    private Reservation checkReservationFindById(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalStateException("Reservation with id" + id + "does not exists"));
        return reservation;
    }

    private void checkReservationExistsOnGivenId(Long id) {
        boolean exists = reservationRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Reservation with id" + id + "does not exists");
        }
    }

    private Reservation convert(NewReservationVo newReservationVo) {
        Reservation reservation = new Reservation();
        reservation.setDate(newReservationVo.getDate());
        reservation.setTime(newReservationVo.getTime());
        reservation.setPlaceId(newReservationVo.getPlaceId());//Placefield dan id getir
        reservation.setUserId(newReservationVo.getUserId());
        reservation.setStatus("Active");
        return reservation;
    }




    private ReservationDTO convert(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setDate(reservation.getDate());
        dto.setTime(reservation.getTime());
        dto.setPlaceId(reservation.getPlaceId());
        dto.setUserId(reservation.getUserId());
        dto.setCreateDate(reservation.getCreateDate());
        dto.setUpdateDate(reservation.getUpdateDate());
        return dto;
    }

    private void updateReservation(Reservation reservation1, UpdateReservationVo updateReservationVo) {
        if (updateReservationVo.getDate() != null && !Objects.equals(reservation1.getDate(), updateReservationVo.getDate())) {
            reservation1.setDate(updateReservationVo.getDate());
            reservation1.setTime(updateReservationVo.getTime());
        }
        reservationRepository.save(reservation1);
    }


    public List<Reservation> getReservationsByUser(ReservationGetVo reservationGetVo) {
        return reservationRepository.findReservationByUserName(reservationGetVo.getUserName());
    }



    public List<ReservationTimeDTO> getReservationsAllEmptyTime(GetEmptyTimeVo getEmptyTimeVo) {
        List<Reservation> reservations = reservationRepository.findReservationByPlaceIdAndDateAndStatus(getEmptyTimeVo.getPlaceId(),getEmptyTimeVo.getDate(),getEmptyTimeVo.getStatus());
        List<ReservationTimeDTO> reservationTimeDTOList = findReservTime(reservations);
        return reservationTimeDTOList;
    }

    public List<ReservationTimeDTO> getReservationsAllEmptySpecialTime(GetEmptySpecialTimeVo getEmptySpecialTimeVo) {
        ArrayList<String> timeSpecialList = findInterval(getEmptySpecialTimeVo.getTimeInterval());
        List<Reservation> reservations = reservationRepository.findReservationByPlaceIdAndDateAndStatus(getEmptySpecialTimeVo.getPlaceId(),getEmptySpecialTimeVo.getDate(),getEmptySpecialTimeVo.getStatus());
        List<ReservationTimeDTO> reservationTimeDTOList = findSpecialReservTime(reservations,timeSpecialList);
        return reservationTimeDTOList;
    }

    private List<ReservationTimeDTO> findSpecialReservTime(List<Reservation> reservations, ArrayList<String> timeSpecialList) {
        List<ReservationTimeDTO> reservationTimeDTOList = new ArrayList<>();
        for(String time: timeSpecialList){
            boolean flag=true;
            String time1 = time.substring(0,5);
            for(Reservation reservation: reservations){
                String time2=reservation.getTime().toString();
                if(time1.equals(time2)==true){
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
                reservationTimeDTO.setTime(time);
                reservationTimeDTOList.add(reservationTimeDTO);
            }
        }
        return reservationTimeDTOList;
    }

    private ArrayList<String> findInterval(String timeInterval) {
        ArrayList<String> arrayList = new ArrayList<>();
        int startIndex=0;
        int lastIndex=0;
        for(String time:timeList){
            if(timeInterval.substring(0,timeInterval.indexOf("-")).equals(time.substring(0,time.indexOf("-")))){
                startIndex = Arrays.asList(timeList).indexOf(time);
            }
            if(timeInterval.substring(timeInterval.indexOf("-")+1,timeInterval.length()).equals(time.substring(0,time.indexOf("-")))){
                lastIndex = Arrays.asList(timeList).indexOf(time);
            }
        }

        for(int i=startIndex;i<lastIndex;i++){
            arrayList.add(timeList[i]);
        }
        return arrayList;
    }

    private String[] timeList = new String[]{
            "00:00:00-01:00:00",
            "01:00:00-02:00:00",
            "09:00:00-10:00:00",
            "10:00:00-11:00:00",
            "11:00:00-12:00:00",
            "12:00:00-13:00:00",
            "13:00:00-14:00:00",
            "14:00:00-15:00:00",
            "15:00:00-16:00:00",
            "16:00:00-17:00:00",
            "17:00:00-18:00:00",
            "18:00:00-19:00:00",
            "19:00:00-20:00:00",
            "20:00:00-21:00:00",
            "21:00:00-22:00:00",
            "22:00:00-23:00:00",
            "23:00:00-00:00:00"
    };

    private List<ReservationTimeDTO> findReservTime(List<Reservation> reservations) {
        List<ReservationTimeDTO> reservationTimeDTOList = new ArrayList<>();
        for(String time: timeList){
            boolean flag=true;
            String time1 = time.substring(0,5);
            for(Reservation reservation: reservations){
                String time2=reservation.getTime().toString();
                if(time1.equals(time2)==true){
                    flag=false;
                    break;
                }
            }
            if(flag==true){
                ReservationTimeDTO reservationTimeDTO = new ReservationTimeDTO();
                reservationTimeDTO.setTime(time);
                reservationTimeDTOList.add(reservationTimeDTO);
            }
        }
        return reservationTimeDTOList;
    }





}
