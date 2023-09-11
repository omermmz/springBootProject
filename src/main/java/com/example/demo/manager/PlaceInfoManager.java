package com.example.demo.manager;

import com.example.demo.model.dto.PlaceInfoDTO;
import com.example.demo.model.entity.PlaceInfo;
import com.example.demo.model.request.NewPlaceInfoRequest;
import com.example.demo.model.vo.NewPlaceInfoVo;
import com.example.demo.service.PlaceInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceInfoManager {

   private final PlaceInfoService placeInfoService;



    public void initializeNewPlaceInfo(NewPlaceInfoRequest newPlaceInfoRequest) {
        NewPlaceInfoVo newPlaceInfoVo = convert(newPlaceInfoRequest);

        placeInfoService.addNewPlaceInfo(newPlaceInfoVo);

    }

    private NewPlaceInfoVo convert (NewPlaceInfoRequest newPlaceInfoRequest){
            NewPlaceInfoVo newPlaceInfoVo = new NewPlaceInfoVo();
            newPlaceInfoVo.setPlaceFieldId(newPlaceInfoRequest.getPlace_field_id());
            newPlaceInfoVo.setType(newPlaceInfoRequest.getType());
            newPlaceInfoVo.setName(newPlaceInfoRequest.getName());
            newPlaceInfoVo.setPrice(newPlaceInfoRequest.getPrice());
            newPlaceInfoVo.setCity_id(newPlaceInfoRequest.getCity_id());
            newPlaceInfoVo.setProvince_id(newPlaceInfoRequest.getProvince_id());
            newPlaceInfoVo.setAddress(newPlaceInfoRequest.getAddress());
            newPlaceInfoVo.setPhone_number(newPlaceInfoRequest.getPhone_number());
            return newPlaceInfoVo;
        }

    public List<PlaceInfoDTO> getPlaceInfos() {
        return placeInfoService.getPlaceInfos();
    }
}

