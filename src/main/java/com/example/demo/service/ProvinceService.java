package com.example.demo.service;

import com.example.demo.model.entity.Province;
import com.example.demo.model.vo.NewProvinceVo;
import com.example.demo.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceService {
    private final ProvinceRepository provinceRepository;

    public void addNewProvince(NewProvinceVo newProvinceVo){
        Province province = new Province();
        province.setName(newProvinceVo.getName());
        province.setCity_id(newProvinceVo.getCityId());
        province = provinceRepository.save(province);
    }
}
