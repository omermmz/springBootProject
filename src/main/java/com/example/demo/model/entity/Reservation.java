package com.example.demo.model.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="rezervation")
public class Reservation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @DateTimeFormat
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "place_name")
    private String placeName;
    @Column(name= "user_name")
    private String userName;


}
