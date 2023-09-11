package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceInfoDTO {
    private Long id;
    private Long placeFieldId;
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
