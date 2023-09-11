package com.example.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPlaceInfoRequest {
    private Long place_field_id;
    private String type;
    private String name;
    private Long price;
    private Long city_id;
    private Long province_id;
    private String address;
    private Long phone_number;
    private String startTime;
    private String endTime;
}
