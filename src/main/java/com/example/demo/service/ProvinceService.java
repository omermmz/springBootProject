package com.example.demo.service;

import com.example.demo.model.entity.Province;
import com.example.demo.model.vo.NewProvinceVo;
import com.example.demo.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService {
    private final ProvinceRepository provinceRepository;

    public Province addNewProvince(NewProvinceVo newProvinceVo){
        Province province = new Province();
        province.setName(newProvinceVo.getName());
        province.setCityId(newProvinceVo.getCityId());
        province = isProvinceExists(province);
        return province;
    }

    private Province isProvinceExists(Province province){
        if(provinceRepository.findByName(province.getName()).isPresent()){
            province = provinceRepository.findByName(province.getName()).orElseThrow(()->new IllegalArgumentException("province doesn't exist"));
            return  province;
        }
        province = provinceRepository.save(province);
        return province;
    }

    public Long getProvinceByName(String provinceName){
        Province province = provinceRepository.findByName(provinceName).orElseThrow(() -> new IllegalArgumentException("Province is not exist"));
        return province.getId();
    }
    public List<Province> getProvincesById(Long id) {
        List<Province> provinces;
        provinces =  provinceRepository.getAllByCityId(id);
        return provinces;
    }
}
