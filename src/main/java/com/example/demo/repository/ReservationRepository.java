package com.example.demo.repository;

import com.example.demo.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Optional<Reservation> findReservationByDate(LocalDateTime date);

    @Query("SELECT res FROM Reservation res WHERE res.userName=?1")
    List<Reservation> findReservationByUserName(String userName);
}
