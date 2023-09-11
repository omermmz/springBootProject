package com.example.demo.model.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityDTO {
    private Long id;
    private String name;
    private Long provinceId;
    private String provinceName;
    private int status;
}
