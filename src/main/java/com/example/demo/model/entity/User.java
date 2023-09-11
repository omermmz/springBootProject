package com.example.demo.model.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_imp")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="status")
    private String status;
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime create_date;
    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime update_date;
}
