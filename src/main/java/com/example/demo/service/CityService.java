package com.example.demo.service;

import com.example.demo.model.dto.CityDTO;
import com.example.demo.model.entity.City;
import com.example.demo.model.vo.NewCityVo;
import com.example.demo.model.vo.NewProvinceVo;
import com.example.demo.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final ProvinceService provinceService;

    public CityDTO addNewCity(NewCityVo newCityVo) {
        City city = new City();
        city.setName(newCityVo.getName());
        city = cityRepository.save(city);

        NewProvinceVo provinceVo = new NewProvinceVo();
        provinceVo.setName(newCityVo.getProvinceName());
        provinceVo.setCityId(city.getId());
        provinceService.addNewProvince(provinceVo);

        return convert(city);
    }

    private CityDTO convert(City city){
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        return cityDTO;
    }
}
