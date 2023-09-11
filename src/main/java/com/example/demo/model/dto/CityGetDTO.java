package com.example.demo.model.dto;

import com.example.demo.model.entity.Province;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityGetDTO {
    private Long id;
    private String name;
    private List<Province> provinces;
}
