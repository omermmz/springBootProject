package com.example.demo.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "place_field")
public class PlaceField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="status")
    private String status;
    @Column(name="company_id")
    private Long company_id;
    @CreationTimestamp
    @Column(name="create_date")
    private LocalDateTime create_date;
    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime update_date;
}
