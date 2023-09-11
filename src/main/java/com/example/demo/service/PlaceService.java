package com.example.demo.service;

import com.example.demo.model.dto.PlaceDTO;
import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.dto.PlaceInfoDTO;
import com.example.demo.model.entity.PlaceField;
import com.example.demo.model.entity.PlaceInfo;
import com.example.demo.model.entity.Province;
import com.example.demo.model.vo.NewPlaceInfoVo;
import com.example.demo.model.vo.NewPlaceVo;
import com.example.demo.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceInfoService placeInfoService;

    private final CityService cityService;

    private final ProvinceService provinceService;

    public PlaceDTO addNewPlace(NewPlaceVo newPlaceVo) {
        PlaceField placeField = convert(newPlaceVo);
        placeField = placeRepository.save(placeField);

        NewPlaceInfoVo newPlaceInfoVo = new NewPlaceInfoVo();
        newPlaceInfoVo = convert2(newPlaceVo);
        newPlaceInfoVo.setPlaceFieldId(placeField.getId());

        PlaceInfoDTO placeInfoDTO = placeInfoService.addNewPlaceInfo(newPlaceInfoVo);
        return convert(placeField,placeInfoDTO);
    }

    public List<PlaceIdDTO> getAllPlaceByCompanyId(Long companyId) {
       List<PlaceField> placeFields = placeRepository.getAllByCompanyId(companyId);
        return fillPlaceIdDTO(placeFields);

    }

    private List<PlaceIdDTO> fillPlaceIdDTO(List<PlaceField> placeFields){
        List<PlaceIdDTO> placeIdDTOList=new ArrayList<>();
        for(PlaceField placeField: placeFields){
            PlaceIdDTO placeIdDTO = new PlaceIdDTO();
            PlaceInfoDTO placeInfoDTO = placeInfoService.getPlaceInfoByPlaceFieldId(placeField.getId());
            placeIdDTO.setId(placeField.getId());
            placeIdDTO.setCompanyId(placeField.getCompanyId());
            placeIdDTO.setPlaceFieldId(placeInfoDTO.getPlaceFieldId());
            placeIdDTO.setType(placeInfoDTO.getType());
            placeIdDTO.setName(placeInfoDTO.getName());
            placeIdDTO.setPrice(placeInfoDTO.getPrice());
            placeIdDTO.setAddress(placeInfoDTO.getAddress());
            placeIdDTO.setPhoneNumber(placeInfoDTO.getPhoneNumber());
            placeIdDTOList.add(placeIdDTO);

        }

        return placeIdDTOList;
    }

    private PlaceField convert(NewPlaceVo newPlaceVo){
        PlaceField placeField = new PlaceField();
        placeField.setStatus("Active");
        placeField.setCompanyId(newPlaceVo.getCompany_id());
        return placeField;
    }

    private PlaceDTO convert(PlaceField placeField,PlaceInfoDTO placeInfoDTO){
        PlaceDTO placeDTO = new PlaceDTO();
        placeDTO.setId(placeField.getId());
        placeDTO.setStatus(placeField.getStatus());
        placeDTO.setCompany_id(placeField.getCompanyId());
        placeDTO.setCreate_date(placeField.getCreateDate());
        placeDTO.setUpdate_date(placeField.getUpdateDate());
        placeDTO.setType(placeInfoDTO.getType());
        placeDTO.setName(placeInfoDTO.getName());
        placeDTO.setPrice(placeInfoDTO.getPrice());
        placeDTO.setCityId(placeInfoDTO.getCityId());
        placeDTO.setProvinceId(placeInfoDTO.getProvinceId());
        placeDTO.setAddress(placeInfoDTO.getAddress());
        placeDTO.setPhoneNumber(placeInfoDTO.getPhoneNumber());
        placeDTO.setKapora(placeInfoDTO.getKapora());
        placeDTO.setStartTime(placeInfoDTO.getStartTime());
        placeDTO.setEndTime(placeInfoDTO.getEndTime());
        return  placeDTO;
    }

    public List<PlaceInfoDTO> getPlaces() {
        return placeInfoService.getPlaceInfos();
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
        newPlaceInfoVo.setKapora(newPlaceVo.getKapora());
        newPlaceInfoVo.setStartTime(newPlaceVo.getStartTime());
        newPlaceInfoVo.setEndTime(newPlaceVo.getEndTime());
        return newPlaceInfoVo;
    }

    public List<PlaceIdDTO> getPlacesById(Long id) {
        PlaceField placeField = placeRepository.findById(id).orElseThrow(()->new IllegalStateException("Place with id" + id + "does not exists"));
        List<PlaceIdDTO> placeIdDTO;
        
        placeIdDTO= placeInfoService.getPlacesByPlaceFieldId(placeField.getId());

        for (PlaceIdDTO placeidDTO:placeIdDTO) {
            placeidDTO.setId(placeField.getId());
            placeidDTO.setStatus(placeField.getStatus());
            placeidDTO.setCompanyId(placeField.getCompanyId());
            placeidDTO.setCreateDate(placeField.getCreateDate());
            placeidDTO.setUpdateDate(placeField.getUpdateDate());
            placeidDTO.setPlaceFieldId(placeField.getId());
        }

        return placeIdDTO;
    }

    public List<PlaceInfoDTO> getPlacesByCity(String cityName) {
        Long cityId = cityService.getCity(cityName);
        return placeInfoService.getPlaceByCity(cityId);
    }

    public List<PlaceInfoDTO> getPlacesByCityAndProvince(String cityName, String provinceName) {
        Long cityId = cityService.getCity(cityName);
        Long provinceId = provinceService.getProvinceByName(provinceName);
        return placeInfoService.getPlaceByCityAndProvince(cityId,provinceId);
    }

    public List<PlaceInfoDTO> getPlacesByCityWithBetweenPrice(String cityName, Long minPrice, Long maxPrice) {
        Long cityId = cityService.getCity(cityName);
        return placeInfoService.getAllPlacesByCityWithBetweenPrice(cityId,minPrice,maxPrice);
    }

    public List<PlaceInfoDTO> getPlacesByCityAndProvinceWithBetweenPrice(String cityName, String provinceName, Long minPrice, Long maxPrice) {
        Long cityId = cityService.getCity(cityName);
        Long provinceId = provinceService.getProvinceByName(provinceName);
        return placeInfoService.getPlaceByCityAndProvinceWithBetweenPrice(cityId,provinceId,minPrice,maxPrice);
    }

    public List<PlaceInfoDTO> getAllPlacesWithBetweenPrice(Long minPrice, Long maxPrice) {
        return placeInfoService.getAllPlacesWithBetweenPrice(minPrice,maxPrice);
    }



}
