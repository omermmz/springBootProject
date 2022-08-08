package com.example.demo.model.dto;


import lombok.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDTO {

    private Long id;
    private LocalDate date;
    private LocalTime time;
    private Long placeId;
    private Long userId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;



}
