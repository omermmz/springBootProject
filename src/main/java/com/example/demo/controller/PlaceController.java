package com.example.demo.controller;


import com.example.demo.manager.PlaceManager;
import com.example.demo.model.dto.PlaceDTO;
import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.dto.PlaceInfoDTO;
import com.example.demo.model.entity.PlaceField;
import com.example.demo.model.request.NewPlaceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/place")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
public class PlaceController {

    private final PlaceManager placeManager;

    @GetMapping
    public List<PlaceInfoDTO> getPlaces(){
        return placeManager.getPLaces();
    }

    @GetMapping(path="/{minPrice}/{maxPrice}")
    public List<PlaceInfoDTO> getAllPlacesWithBetweenPrice(@PathVariable("minPrice") Long minPrice,@PathVariable("maxPrice") Long maxPrice){
        return placeManager.getAllPlacesWithBetweenPrice(minPrice,maxPrice);
    }
    @GetMapping(path ="/getByCity/{city}" )
    public List<PlaceInfoDTO> getPlaceByCity(@PathVariable("city") String cityName){
        return placeManager.getPlacesByCity(cityName);
    }

    @GetMapping(path ="/getByCity/{city}/{province}" )
    public List<PlaceInfoDTO> getPlaceByCityAndProvince(@PathVariable("city") String cityName,@PathVariable("province") String provinceName){
        return placeManager.getPlacesByCityAndProvince(cityName,provinceName);
    }
    @GetMapping(path = "/getByCity/{city}/{minPrice}/{maxPrice}")
    public List<PlaceInfoDTO> getPlaceByCityWithBetweenPrice(@PathVariable("city") String cityName,@PathVariable("minPrice") Long minPrice,@PathVariable("maxPrice") Long maxPrice){
        return placeManager.getPlacesByCityWithBetweenPrice(cityName,minPrice,maxPrice);
    }
    @GetMapping(path = "/getByCity/{city}/{province}/{minPrice}/{maxPrice}")
    public List<PlaceInfoDTO> getPlaceByCityAndProvinceWithBetweenPrice(@PathVariable ("city") String cityName,@PathVariable("province") String provinceName, @PathVariable("minPrice") Long minPrice,@PathVariable("maxPrice") Long maxPrice){
        return placeManager.getPlacesByCityAndProvinceWithBetweenPrice(cityName,provinceName,minPrice,maxPrice);
        // WARNİNG!!!!: yapılan requestte sınırlar dahil olarak sorgu yapılıyor
    }
    @GetMapping(path = "{placeId}")
    public List<PlaceIdDTO> getPlacesById(@PathVariable("placeId") Long id){
        return placeManager.getPLacesById(id);
    }

    @PostMapping
    public PlaceDTO registerNewPlace(@RequestBody NewPlaceRequest newPlaceRequest){
        return placeManager.initializePlace(newPlaceRequest);
    }

    @PutMapping(path="{placeId},{status}")
    public void updatingPlace(@PathVariable("placeId") Long id,@PathVariable("status") String status){
        placeManager.updatePlace(id,status);
    }



}
