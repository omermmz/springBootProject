package com.example.demo.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyDTO {
    private Long id;
    private String status;
    private String name;
    private LocalDateTime create_date;
    private LocalDateTime update_date;
}
