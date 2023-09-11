package com.example.demo.repository;

import com.example.demo.model.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {

    List<Province> getAllByCityId(Long id);
    Optional<Province> findByName(String name);
}

