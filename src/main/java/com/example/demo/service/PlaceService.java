package com.example.demo.service;

import com.example.demo.model.dto.PlaceDTO;
import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.entity.PlaceField;
import com.example.demo.model.vo.NewPlaceInfoVo;
import com.example.demo.model.vo.NewPlaceVo;
import com.example.demo.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceInfoService placeInfoService;

    public PlaceDTO addNewPlace(NewPlaceVo newPlaceVo) {
        PlaceField placeField = convert(newPlaceVo);
        placeField = placeRepository.save(placeField);

        NewPlaceInfoVo newPlaceInfoVo = new NewPlaceInfoVo();
        newPlaceInfoVo = convert2(newPlaceVo);
        newPlaceInfoVo.setPlaceFieldId(placeField.getId());

        placeInfoService.addNewPlaceInfo(newPlaceInfoVo);
        return convert(placeField);
    }

    private PlaceField convert(NewPlaceVo newPlaceVo){
        PlaceField placeField = new PlaceField();
        placeField.setStatus(newPlaceVo.getStatus());
        placeField.setCompany_id(newPlaceVo.getCompany_id());
        return placeField;
    }

    private PlaceDTO convert(PlaceField placeField){
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(placeField.getId());
        placeDTO.setStatus(placeField.getStatus());
        placeDTO.setCompany_id(placeField.getCompany_id());
        placeDTO.setCreate_date(placeField.getCreate_date());
        placeDTO.setUpdate_date(placeField.getUpdate_date());
        return  placeDTO;
    }

    public List<PlaceField> getPlaces() {
        return placeRepository.findAll();
    }

    @Transactional
    public void updatePlaceStatus(Long id,String status) {
        PlaceField placeField = checkPlaceFindById(id);
        updatePlace(placeField,status);

    }

    private PlaceField checkPlaceFindById(Long id){
        PlaceField placeField = placeRepository.findById(id).orElseThrow(()->new IllegalStateException("Place with id" + id + "does not exists"));
        return placeField;
    }

    private void updatePlace(PlaceField placeField,String status){
        if(!Objects.equals(placeField.getStatus(),status)){
            placeField.setStatus(status);
        }
        placeRepository.save(placeField);
    }

    private NewPlaceInfoVo convert2(NewPlaceVo newPlaceVo){
        NewPlaceInfoVo newPlaceInfoVo = new NewPlaceInfoVo();
        newPlaceInfoVo.setType(newPlaceVo.getType());
        newPlaceInfoVo.setName(newPlaceVo.getName());
        newPlaceInfoVo.setPrice(newPlaceVo.getPrice());
        newPlaceInfoVo.setCity_id(newPlaceVo.getCity_id());
        newPlaceInfoVo.setProvince_id(newPlaceVo.getProvince_id());
        newPlaceInfoVo.setAddress(newPlaceVo.getAddress());
        newPlaceInfoVo.setPhone_number(newPlaceVo.getPhone_number());
        return newPlaceInfoVo;
    }

    public List<PlaceIdDTO> getPlacesById(Long id) {
        PlaceField placeField = placeRepository.findById(id).orElseThrow(()->new IllegalStateException("Place with id" + id + "does not exists"));
        List<PlaceIdDTO> placeIdDTO;
        
        placeIdDTO= placeInfoService.getPlacesByPlaceFieldId(placeField.getId());

        for (PlaceIdDTO placeidDTO:placeIdDTO) {
            placeidDTO.setId(placeField.getId());
            placeidDTO.setStatus(placeField.getStatus());
            placeidDTO.setCompany_id(placeField.getCompany_id());
            placeidDTO.setCreate_date(placeField.getCreate_date());
            placeidDTO.setUpdate_date(placeField.getUpdate_date());
            placeidDTO.setPlace_field_id(placeField.getId());
        }

        return placeIdDTO;
    }
}
