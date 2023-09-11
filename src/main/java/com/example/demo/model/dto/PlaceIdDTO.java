package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceIdDTO {
    private Long id;
    private String status;
    private Long companyId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Long placeFieldId;
    private String type;
    private String name;
    private Long price;
    private Long cityId;
    private Long provinceId;
    private String address;
    private Long phoneNumber;
}
