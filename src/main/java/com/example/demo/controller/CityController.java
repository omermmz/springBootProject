package com.example.demo.controller;

import com.example.demo.manager.CityManager;
import com.example.demo.model.request.NewCityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityManager cityManager;

    @PostMapping
    public void registerNewCity(@RequestBody NewCityRequest newCityRequest){
        cityManager.initializeCity(newCityRequest);
    }
}
