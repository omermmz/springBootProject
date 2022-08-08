package com.example.demo.model.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewReservationRequest {

    private LocalDate date;
    private LocalTime time;
    private Long placeId;
    private Long userId;
}
