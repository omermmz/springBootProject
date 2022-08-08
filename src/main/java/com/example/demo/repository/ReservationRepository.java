package com.example.demo.repository;

import com.example.demo.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Optional<Reservation> findReservationByDateAndTimeAndPlaceId(LocalDate date, LocalTime time,Long placeId);

    @Query("SELECT res FROM Reservation res WHERE res.userId=?1")
    List<Reservation> findReservationByUserName(String userName);

    List<Reservation> findReservationByPlaceIdAndDateAndStatus(Long placeId,LocalDate date,String status);
}
