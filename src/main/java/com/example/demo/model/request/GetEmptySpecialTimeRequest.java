package com.example.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetEmptySpecialTimeRequest {
    private Long placeId;
    private LocalDate date;
    private String status;
    private String timeInterval;
}
