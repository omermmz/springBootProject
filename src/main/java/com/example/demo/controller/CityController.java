package com.example.demo.controller;

import com.example.demo.manager.CityManager;
import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.CityGetDTO;
import com.example.demo.model.entity.City;
import com.example.demo.model.request.NewCityRequest;
import com.example.demo.service.CityService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityManager cityManager;
    private final CityService cityService;
    @GetMapping
    public List<CityGetDTO> getCity(){

        return cityManager.getCities();
    }
    @PostMapping
    public CityDTO registerNewCity(@RequestBody NewCityRequest newCityRequest){
        return cityManager.initializeCity(newCityRequest);
    }


}
