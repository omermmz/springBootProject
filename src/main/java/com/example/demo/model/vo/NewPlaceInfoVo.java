package com.example.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPlaceInfoVo {
    private Long placeFieldId;
    private String type;
    private String name;
    private Long price;
    private Long city_id;
    private Long province_id;
    private String address;
    private Long phone_number;
}
