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
}
