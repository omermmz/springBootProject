package com.example.demo.manager;

import com.example.demo.model.request.NewCityRequest;
import com.example.demo.model.vo.NewCityVo;
import com.example.demo.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityManager {
    private final CityService cityService;

    public void initializeCity(NewCityRequest newCityRequest) {
        NewCityVo newCityVo = new NewCityVo();
        newCityVo.setName(newCityRequest.getName());
        newCityVo.setProvinceName(newCityRequest.getProvinceName());
        cityService.addNewCity(newCityVo);
    }
}
