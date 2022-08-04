package com.example.demo.model.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewReservationRequest {

    private LocalDateTime date;
    private String placeName;
    private String userName;
}
