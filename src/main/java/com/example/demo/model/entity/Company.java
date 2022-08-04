package com.example.demo.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @CreationTimestamp
    @Column(name = "create_date",nullable = true)
    private LocalDateTime create_date;
    @Column(name="name")
    private String name;
    @Column(name="status")
    private String status;
    @UpdateTimestamp
    @Column(name = "update_date",nullable = true)
    private LocalDateTime update_date;
}
