package com.example.demo.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProvinceDTO {
    private Long id;
    private String name;
    private Long cityId;
}
