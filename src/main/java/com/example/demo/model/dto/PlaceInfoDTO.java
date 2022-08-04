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
    private Long place_field_id;
    private String type;
    private String name;
    private Long price;
    private Long city_id;
    private Long province_id;
    private String address;
    private Long phone_number;
}
