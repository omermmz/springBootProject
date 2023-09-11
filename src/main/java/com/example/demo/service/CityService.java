package com.example.demo.service;

import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.dto.CityGetDTO;
import com.example.demo.model.dto.WhoAmIDTO;
import com.example.demo.model.entity.City;
import com.example.demo.model.entity.Province;
import com.example.demo.model.vo.NewCityVo;
import com.example.demo.model.vo.NewProvinceVo;
import com.example.demo.repository.CityRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final ProvinceService provinceService;
    private final UserCredentialService userCredentialService;

    public CityDTO addNewCity(NewCityVo newCityVo) {
        City city = new City();
        Province province;
        city.setName(newCityVo.getName());
        city = cityIsExist(city);
        NewProvinceVo provinceVo = new NewProvinceVo();
        provinceVo.setName(newCityVo.getProvinceName());
        provinceVo.setCityId(city.getId());
        province = provinceService.addNewProvince(provinceVo);

        return convert(city,province);
    }

    public Long getCity(String cityName){
        City city = cityRepository.findCityByName(cityName).orElseThrow(() -> new IllegalArgumentException("City is not exist"));
        return city.getId();
    }

    private CityDTO convert(City city,Province province){
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setProvinceId(province.getId());
        cityDTO.setProvinceName(province.getName());
        cityDTO.setStatus(HttpStatus.OK.value());
        return cityDTO;
    }

    private City cityIsExist(City city){
        if(cityRepository.findCityByName(city.getName()).isPresent()){
            city = cityRepository.findCityByName(city.getName()).orElseThrow(()-> new IllegalArgumentException("city not found"));
            return city;
        }
        city = cityRepository.save(city);
        return city;
    }

    public List<CityGetDTO> getCities() {
        List<City> cities;
        cities =  cityRepository.findAll();
        List<CityGetDTO> cityGetDTO;
        cityGetDTO = fillCityGetDTO(cities);

        return cityGetDTO;
    }


    private List<CityGetDTO> fillCityGetDTO(List<City> cities){
        List<CityGetDTO> cityGetDTOS=new ArrayList<>();
        for (City city : cities){
            CityGetDTO cityGetDTO = new CityGetDTO();
            cityGetDTO.setId(city.getId());
            cityGetDTO.setName(city.getName());
            List<Province> provinces;
            provinces = provinceService.getProvincesById(city.getId());
            cityGetDTO.setProvinces(provinces);
            cityGetDTOS.add(cityGetDTO);
        }
        return cityGetDTOS;
    }





}
