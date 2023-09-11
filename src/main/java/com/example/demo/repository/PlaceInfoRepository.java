package com.example.demo.repository;

import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.entity.PlaceInfo;
import com.example.demo.model.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceInfoRepository extends JpaRepository<PlaceInfo,Long> {
    Optional<PlaceInfo> findPlaceInfoByAddress(String address);
    List<PlaceInfo> getPlaceInfoByPlaceFieldId(Long id);

    List<PlaceInfo> findPlaceInfoByCityId(Long id);

    List<PlaceInfo> findPlaceInfoByCityIdAndProvinceId(Long cityId,Long provinceId);

    List<PlaceInfo> findPlaceInfoByCityIdAndProvinceIdAndPriceBetween(Long cityId,Long provinceId,Long minPrice,Long maxPrice);
    List<PlaceInfo> findPlaceInfoByPriceBetween(Long minPrice,Long maxPrice);
    List<PlaceInfo> findPlaceInfoByCityIdAndPriceBetween(Long cityId,Long minPrice,Long maxPrice);

    PlaceInfo getById(Long id);
    PlaceInfo findPlaceInfoByName(String name);
    Optional<PlaceInfo> findPlaceInfoByPlaceFieldId(Long placeFieldId);

}
