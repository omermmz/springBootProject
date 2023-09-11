package com.example.demo.manager;

import com.example.demo.model.dto.PlaceDTO;
import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.dto.PlaceInfoDTO;
import com.example.demo.model.entity.PlaceField;
import com.example.demo.model.request.NewPlaceInfoRequest;
import com.example.demo.model.request.NewPlaceRequest;
import com.example.demo.model.vo.NewPlaceInfoVo;
import com.example.demo.model.vo.NewPlaceVo;
import com.example.demo.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceManager {

    private final PlaceService placeService;

    public PlaceDTO initializePlace(NewPlaceRequest newPlaceRequest) {
        NewPlaceVo newPlaceVo = new NewPlaceVo();
        //newPlaceVo.setStatus(newPlaceRequest.getStatus());
        newPlaceVo.setCompany_id(newPlaceRequest.getCompany_id());
        newPlaceVo.setType(newPlaceRequest.getType());
        newPlaceVo.setName(newPlaceRequest.getName());
        newPlaceVo.setPrice(newPlaceRequest.getPrice());
        newPlaceVo.setCity_id(newPlaceRequest.getCity_id());
        newPlaceVo.setProvince_id(newPlaceRequest.getProvince_id());
        newPlaceVo.setAddress(newPlaceRequest.getAddress());
        newPlaceVo.setPhone_number(newPlaceRequest.getPhone_number());
        newPlaceVo.setKapora(newPlaceRequest.getKapora());
        newPlaceVo.setStartTime(newPlaceRequest.getStart_time());
        newPlaceVo.setEndTime(newPlaceRequest.getEnd_time());
       return placeService.addNewPlace(newPlaceVo);
    }

    public List<PlaceInfoDTO> getPLaces() {
        return placeService.getPlaces();
    }

    public void updatePlace(Long id,String status) {
        placeService.updatePlaceStatus(id,status);
    }


    public List<PlaceIdDTO> getPLacesById(Long id) {
        return placeService.getPlacesById(id);
    }

    public List<PlaceInfoDTO> getPlacesByCity(String cityName) {
        return placeService.getPlacesByCity(cityName);
    }

    public List<PlaceInfoDTO> getPlacesByCityAndProvince(String cityName, String provinceName) {
        return placeService.getPlacesByCityAndProvince(cityName,provinceName);
    }

    public List<PlaceInfoDTO> getPlacesByCityAndProvinceWithBetweenPrice(String cityName, String provinceName, Long minPrice, Long maxPrice) {
        return placeService.getPlacesByCityAndProvinceWithBetweenPrice(cityName,provinceName,minPrice,maxPrice);
    }

    public List<PlaceInfoDTO> getAllPlacesWithBetweenPrice(Long minPrice, Long maxPrice) {
        return placeService.getAllPlacesWithBetweenPrice(minPrice,maxPrice);
    }

    public List<PlaceInfoDTO> getPlacesByCityWithBetweenPrice(String cityName, Long minPrice, Long maxPrice) {
        return placeService.getPlacesByCityWithBetweenPrice(cityName,minPrice,maxPrice);
    }
}
