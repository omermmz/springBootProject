package com.example.demo.model.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "place_info")
public class PlaceInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "place_field_id")
    private Long placeFieldId;
    @Column(name = "type")
    private String type;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Long price;
    @Column(name = "city_id")
    private Long cityId;
    @Column(name = "province_id")
    private Long provinceId;
    @Column(name = "address")
    private String address;
    @Column(name="phone_number")
    private Long phoneNumber;
    @Column(name = "kapora")
    private Long kapora;
    @Column(name="start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
}
