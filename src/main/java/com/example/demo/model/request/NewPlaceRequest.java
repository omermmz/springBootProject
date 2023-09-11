package com.example.demo.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewPlaceRequest {
    //private String status;
    private Long company_id;
    private String type;
    private String name;
    private Long price;
    private Long city_id;
    private Long province_id;
    private String address;
    private Long phone_number;
    private Long kapora;
    private String start_time;
    private String end_time;
}
