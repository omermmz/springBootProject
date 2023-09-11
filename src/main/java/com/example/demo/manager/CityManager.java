package com.example.demo.manager;

import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.CityGetDTO;
import com.example.demo.model.entity.City;
import com.example.demo.model.request.NewCityRequest;
import com.example.demo.model.vo.NewCityVo;
import com.example.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityManager {
    private final CityService cityService;

    public CityDTO initializeCity(NewCityRequest newCityRequest) {
        NewCityVo newCityVo = new NewCityVo();
        newCityVo.setName(newCityRequest.getName());
        newCityVo.setProvinceName(newCityRequest.getProvinceName());
        return cityService.addNewCity(newCityVo);
    }

    public List<CityGetDTO> getCities() {
        return cityService.getCities();
    }
}
