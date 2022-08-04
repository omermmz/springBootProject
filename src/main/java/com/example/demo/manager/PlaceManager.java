package com.example.demo.manager;

import com.example.demo.model.dto.PlaceIdDTO;
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

    public void initializePlace(NewPlaceRequest newPlaceRequest) {
        NewPlaceVo newPlaceVo = new NewPlaceVo();
        newPlaceVo.setStatus(newPlaceRequest.getStatus());
        newPlaceVo.setCompany_id(newPlaceRequest.getCompany_id());
        newPlaceVo.setType(newPlaceRequest.getType());
        newPlaceVo.setName(newPlaceRequest.getName());
        newPlaceVo.setPrice(newPlaceRequest.getPrice());
        newPlaceVo.setCity_id(newPlaceRequest.getCity_id());
        newPlaceVo.setProvince_id(newPlaceRequest.getProvince_id());
        newPlaceVo.setAddress(newPlaceRequest.getAddress());
        newPlaceVo.setPhone_number(newPlaceRequest.getPhone_number());
        placeService.addNewPlace(newPlaceVo);
    }

    public List<PlaceField> getPLaces() {
        return placeService.getPlaces();
    }

    public void updatePlace(Long id,String status) {
        placeService.updatePlaceStatus(id,status);
    }


    public List<PlaceIdDTO> getPLacesById(Long id) {
        return placeService.getPlacesById(id);
    }
}
