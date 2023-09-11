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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    public PlaceInfoDTO getPlaceInfoByPlaceFieldId(Long placeFieldId){
        PlaceInfo placeInfo = placeInfoRepository.findPlaceInfoByPlaceFieldId(placeFieldId).orElseThrow(()-> new UsernameNotFoundException("placeInfo is not exist"));
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
        placeInfo.setCityId(newPlaceInfoVo.getCity_id());
        placeInfo.setProvinceId(newPlaceInfoVo.getProvince_id());
        placeInfo.setAddress(newPlaceInfoVo.getAddress());
        placeInfo.setPhoneNumber(newPlaceInfoVo.getPhone_number());
        placeInfo.setKapora(newPlaceInfoVo.getKapora());
        placeInfo.setStartTime(newPlaceInfoVo.getStartTime());
        placeInfo.setEndTime(newPlaceInfoVo.getEndTime());
        return placeInfo;
    }

    private PlaceInfoDTO convert(PlaceInfo placeInfo){
        PlaceInfoDTO placeInfoDTO = new PlaceInfoDTO();
        placeInfoDTO.setPlaceFieldId(placeInfo.getPlaceFieldId());
        placeInfoDTO.setType(placeInfo.getType());
        placeInfoDTO.setName(placeInfo.getName());
        placeInfoDTO.setPrice(placeInfo.getPrice());
        placeInfoDTO.setCityId(placeInfo.getCityId());
        placeInfoDTO.setProvinceId(placeInfo.getProvinceId());
        placeInfoDTO.setAddress(placeInfo.getAddress());
        placeInfoDTO.setPhoneNumber(placeInfo.getPhoneNumber());
        placeInfoDTO.setKapora(placeInfo.getKapora());
        placeInfoDTO.setStartTime(placeInfo.getStartTime());
        placeInfoDTO.setEndTime(placeInfo.getEndTime());
        return placeInfoDTO;
    }

    public List<PlaceInfoDTO> getPlaceInfos() {
        List<PlaceInfo> placeInfos;
        placeInfos = placeInfoRepository.findAll();
        List<PlaceInfoDTO> placeInfoDTOS;
        placeInfoDTOS = convertPlaceInfos(placeInfos);
        return placeInfoDTOS;
    }

    public PlaceInfoDTO getPlaceById(Long id){
        PlaceInfo placeInfo = placeInfoRepository.getById(id);
        PlaceInfoDTO placeInfoDTO = convert(placeInfo);
        return placeInfoDTO;
    }

    public List<PlaceInfoDTO> getPlaceByCity(Long cityId){
        return convertPlaceInfos(placeInfoRepository.findPlaceInfoByCityId(cityId));
    }

    public List<PlaceInfoDTO> getPlaceByCityAndProvince(Long cityId, Long provinceId) {
        return convertPlaceInfos(placeInfoRepository.findPlaceInfoByCityIdAndProvinceId(cityId,provinceId));
    }

    public List<PlaceInfoDTO> getPlaceByCityAndProvinceWithBetweenPrice(Long cityId, Long provinceId, Long minPrice, Long maxPrice) {
        return convertPlaceInfos(placeInfoRepository.findPlaceInfoByCityIdAndProvinceIdAndPriceBetween(cityId,provinceId,minPrice,maxPrice));
    }

    public List<PlaceInfoDTO> getAllPlacesWithBetweenPrice(Long minPrice, Long maxPrice) {
        return convertPlaceInfos(placeInfoRepository.findPlaceInfoByPriceBetween(minPrice,maxPrice));
    }

    public List<PlaceInfoDTO> getAllPlacesByCityWithBetweenPrice(Long cityId, Long minPrice, Long maxPrice) {
        return convertPlaceInfos(placeInfoRepository.findPlaceInfoByCityIdAndPriceBetween(cityId,minPrice,maxPrice));
    }

    private List<PlaceInfoDTO> convertPlaceInfos(List<PlaceInfo> placeInfos){
        List<PlaceInfoDTO> placeInfoDTOS= new ArrayList<>();
        for(PlaceInfo placeInfo: placeInfos){
            PlaceInfoDTO placeInfoDTO= new PlaceInfoDTO();
            placeInfoDTO.setId(placeInfo.getId());
            placeInfoDTO.setPlaceFieldId(placeInfo.getPlaceFieldId());
            placeInfoDTO.setType(placeInfo.getType());
            placeInfoDTO.setName(placeInfo.getName());
            placeInfoDTO.setPrice(placeInfo.getPrice());
            placeInfoDTO.setCityId(placeInfo.getCityId());
            placeInfoDTO.setProvinceId(placeInfo.getProvinceId());
            placeInfoDTO.setAddress(placeInfo.getAddress());
            placeInfoDTO.setPhoneNumber(placeInfo.getPhoneNumber());
            placeInfoDTO.setKapora(placeInfo.getKapora());
            placeInfoDTO.setStartTime(placeInfo.getStartTime());
            placeInfoDTO.setEndTime(placeInfo.getEndTime());
            placeInfoDTOS.add(placeInfoDTO);
        }
        return placeInfoDTOS;
    }

    public List<PlaceIdDTO> getPlacesByPlaceFieldId(Long id) {
        return convertListDTO(placeInfoRepository.getPlaceInfoByPlaceFieldId(id));
    }

    private List<PlaceIdDTO> convertListDTO(List<PlaceInfo> placeInfoList){
        List<PlaceIdDTO> placeIdDTOList = new ArrayList<>();
        PlaceIdDTO placeIdDTO = new PlaceIdDTO();
        for(PlaceInfo placeInfo:placeInfoList){
                placeIdDTO.setType(placeInfo.getType());
                placeIdDTO.setName(placeInfo.getName());
                placeIdDTO.setPrice(placeInfo.getPrice());
                placeIdDTO.setCityId(placeInfo.getCityId());
                placeIdDTO.setProvinceId(placeInfo.getProvinceId());
                placeIdDTO.setAddress(placeInfo.getAddress());
                placeIdDTO.setPhoneNumber(placeInfo.getPhoneNumber());
                placeIdDTOList.add(placeIdDTO);
        }
        return placeIdDTOList;
    }



}
