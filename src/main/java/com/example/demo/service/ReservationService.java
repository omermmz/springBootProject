package com.example.demo.service;


import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.model.entity.Reservation;
import com.example.demo.model.vo.NewReservationVo;
import com.example.demo.model.vo.ReservationGetVo;
import com.example.demo.model.vo.UpdateReservationVo;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public List<Reservation> getReservations() {
        return reservationRepository.findAll();

    }

    public ReservationDTO addNewReservation(NewReservationVo newReservationVo) {
        checkReservationExistsOnGivenDate(newReservationVo.getDate());
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

    private void checkReservationExistsOnGivenDate(LocalDateTime date) {
         reservationRepository.findReservationByDate(date)
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
        reservation.setPlaceName(newReservationVo.getPlaceName());
        reservation.setUserName(newReservationVo.getUserName());
        return reservation;
    }




    private ReservationDTO convert(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setDate(reservation.getDate());
        return dto;
    }

    private void updateReservation(Reservation reservation1, UpdateReservationVo updateReservationVo) {
        if (updateReservationVo.getDateTime() != null && !Objects.equals(reservation1.getDate(), updateReservationVo.getDateTime())) {
            reservation1.setDate(updateReservationVo.getDateTime());
        }
        reservationRepository.save(reservation1);
    }


    public List<Reservation> getReservationsByUser(ReservationGetVo reservationGetVo) {
        return reservationRepository.findReservationByUserName(reservationGetVo.getUserName());
    }
}
