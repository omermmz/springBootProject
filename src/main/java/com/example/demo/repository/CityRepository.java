package com.example.demo.repository;

import com.example.demo.model.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {
    List<City> findAll();
    Optional<City> findCityByName(String name);
}
