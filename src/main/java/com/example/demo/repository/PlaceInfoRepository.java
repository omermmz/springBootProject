package com.example.demo.repository;

import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.entity.PlaceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceInfoRepository extends JpaRepository<PlaceInfo,Long> {
    Optional<PlaceInfo> findPlaceInfoByAddress(String address);
    List<PlaceInfo> findPlaceInfoByPlaceFieldId(Long id);
}
