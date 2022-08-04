package com.example.demo.manager;

import com.example.demo.model.entity.Reservation;
import com.example.demo.model.request.ReservationGetRequest;
import com.example.demo.model.request.UpdateReservationRequest;
import com.example.demo.model.vo.ReservationGetVo;
import com.example.demo.model.vo.UpdateReservationVo;
import com.example.demo.model.request.NewReservationRequest;
import com.example.demo.model.vo.NewReservationVo;
import com.example.demo.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResarvationManager {

    private final ReservationService reservationService;

    public void initializeReservation(NewReservationRequest newReservationRequest){
        NewReservationVo newReservationVo = new NewReservationVo();
        newReservationVo.setDate(newReservationRequest.getDate());
        newReservationVo.setPlaceName(newReservationRequest.getPlaceName());
        newReservationVo.setUserName(newReservationRequest.getUserName());

        reservationService.addNewReservation(newReservationVo);
    }

    public void removeReservation(Long reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    public void  updateReservation(UpdateReservationRequest updateReservationRequest) {
        UpdateReservationVo updateReservationVo = new UpdateReservationVo();
        updateReservationVo.setId(updateReservationRequest.getId());
        updateReservationVo.setDateTime(updateReservationRequest.getDateTime());
        updateReservationVo.setUserName(updateReservationRequest.getUserName());

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
}
