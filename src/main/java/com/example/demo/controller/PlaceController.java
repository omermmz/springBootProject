package com.example.demo.controller;


import com.example.demo.manager.PlaceManager;
import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.entity.PlaceField;
import com.example.demo.model.request.NewPlaceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceManager placeManager;

    @GetMapping
    public List<PlaceField> getPlaces(){
        return placeManager.getPLaces();
    }

    @GetMapping(path = "{placeId}")
    public List<PlaceIdDTO> getPlacesById(@PathVariable("placeId") Long id){
        return placeManager.getPLacesById(id);
    }

    @PostMapping
    public void registerNewPlace(@RequestBody NewPlaceRequest newPlaceRequest){
        placeManager.initializePlace(newPlaceRequest);
    }

    @PutMapping(path="{placeId},{status}")
    public void updatingPlace(@PathVariable("placeId") Long id,@PathVariable("status") String status){
        placeManager.updatePlace(id,status);
    }

}
