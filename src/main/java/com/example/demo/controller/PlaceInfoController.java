package com.example.demo.controller;

import com.example.demo.manager.PlaceInfoManager;
import com.example.demo.model.entity.PlaceInfo;
import com.example.demo.model.request.NewPlaceInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/placeinfo")
@RequiredArgsConstructor
public class PlaceInfoController {

    private final PlaceInfoManager placeInfoManager;



    @GetMapping
    public List<PlaceInfo> getPlaceInfos(){
        return placeInfoManager.getPlaceInfos();
    }

    @PostMapping
    public void registerNewPlaceInfo(@RequestBody NewPlaceInfoRequest newPlaceInfoRequest){
        placeInfoManager.initializeNewPlaceInfo(newPlaceInfoRequest);
    }

}
