package com.example.demo.controller;

import com.example.demo.manager.PlaceInfoManager;
import com.example.demo.model.dto.PlaceInfoDTO;
import com.example.demo.model.entity.PlaceInfo;
import com.example.demo.model.request.NewPlaceInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/placeinfo")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true")
public class PlaceInfoController {

    private final PlaceInfoManager placeInfoManager;



    @GetMapping
    public List<PlaceInfoDTO> getPlaceInfos(){
        return placeInfoManager.getPlaceInfos();
    }

    @PostMapping
    public void registerNewPlaceInfo(@RequestBody NewPlaceInfoRequest newPlaceInfoRequest){
        placeInfoManager.initializeNewPlaceInfo(newPlaceInfoRequest);
    }

}
