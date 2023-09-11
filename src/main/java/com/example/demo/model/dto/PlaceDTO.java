package com.example.demo.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlaceDTO {
    private Long id;
    private String status;
    private Long company_id;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
    private String type;
    private String name;
    private Long price;
    private Long cityId;
    private Long provinceId;
    private String address;
    private Long phoneNumber;
    private Long kapora;
    private String startTime;
    private String endTime;
}
