package com.example.demo.service;

import com.example.demo.manager.PlaceInfoManager;
import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.dto.PlaceInfoDTO;
import com.example.demo.model.entity.PlaceInfo;
import com.example.demo.model.vo.NewPlaceInfoVo;
import com.example.demo.repository.PlaceInfoRepository;
import com.example.demo.repository.PlaceRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceInfoService {

    private final PlaceInfoRepository placeInfoRepository;


    public PlaceInfoDTO addNewPlaceInfo(NewPlaceInfoVo newPlaceInfoVo) {
        checkPlaceInfoOnGivenAddress(newPlaceInfoVo.getAddress());
        PlaceInfo placeInfo = convert(newPlaceInfoVo);
        placeInfo = placeInfoRepository.save(placeInfo);
        return convert(placeInfo);

    }

    private void checkPlaceInfoOnGivenAddress(String address) {
        placeInfoRepository.findPlaceInfoByAddress(address).ifPresent(placeInfo -> {
            throw new IllegalStateException("Place is exists");
        });
    }

    private PlaceInfo convert(NewPlaceInfoVo newPlaceInfoVo){
        PlaceInfo placeInfo = new PlaceInfo();
        placeInfo.setPlaceFieldId(newPlaceInfoVo.getPlaceFieldId());
        placeInfo.setType(newPlaceInfoVo.getType());
        placeInfo.setName(newPlaceInfoVo.getName());
        placeInfo.setPrice(newPlaceInfoVo.getPrice());
        placeInfo.setCity_id(newPlaceInfoVo.getCity_id());
        placeInfo.setProvince_id(newPlaceInfoVo.getProvince_id());
        placeInfo.setAddress(newPlaceInfoVo.getAddress());
        placeInfo.setPhone_number(newPlaceInfoVo.getPhone_number());
        return placeInfo;
    }

    private PlaceInfoDTO convert(PlaceInfo placeInfo){
        PlaceInfoDTO placeInfoDTO = new PlaceInfoDTO();
        placeInfoDTO.setPlace_field_id(placeInfo.getPlaceFieldId());
        placeInfoDTO.setType(placeInfo.getType());
        placeInfoDTO.setName(placeInfo.getName());
        placeInfoDTO.setPrice(placeInfo.getPrice());
        placeInfoDTO.setCity_id(placeInfo.getCity_id());
        placeInfoDTO.setProvince_id(placeInfo.getProvince_id());
        placeInfoDTO.setAddress(placeInfo.getAddress());
        placeInfoDTO.setPhone_number(placeInfo.getPhone_number());
        return placeInfoDTO;
    }

    public List<PlaceInfo> getPlaceInfos() {
        return placeInfoRepository.findAll();
    }

    public List<PlaceIdDTO> getPlacesByPlaceFieldId(Long id) {
        return convertListDTO(placeInfoRepository.findPlaceInfoByPlaceFieldId(id));
    }

    private List<PlaceIdDTO> convertListDTO(List<PlaceInfo> placeInfoList){
        List<PlaceIdDTO> placeIdDTOList = new ArrayList<>();
        PlaceIdDTO placeIdDTO = new PlaceIdDTO();
        for(PlaceInfo placeInfo:placeInfoList){
                placeIdDTO.setType(placeInfo.getType());
                placeIdDTO.setName(placeInfo.getName());
                placeIdDTO.setPrice(placeInfo.getPrice());
                placeIdDTO.setCity_id(placeInfo.getCity_id());
                placeIdDTO.setProvince_id(placeInfo.getProvince_id());
                placeIdDTO.setAddress(placeInfo.getAddress());
                placeIdDTO.setPhone_number(placeInfo.getPhone_number());
                placeIdDTOList.add(placeIdDTO);
        }
        return placeIdDTOList;
    }
}
