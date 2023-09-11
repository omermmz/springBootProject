package com.example.demo.manager;

import com.example.demo.model.dto.CompanyUserIdDTO;
import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.dto.WhoAmIDTO;
import com.example.demo.model.request.GetAllPlaceRequest;
import com.example.demo.model.request.NewCompanyUserRequest;
import com.example.demo.model.vo.NewCompanyUserVo;
import com.example.demo.service.CompanyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyUserManager {
    private final CompanyUserService companyUserService;

    public void initializeCompanyUser(NewCompanyUserRequest newCompanyUserRequest) {
        NewCompanyUserVo newCompanyUserVo = convert(newCompanyUserRequest);
        companyUserService.addNewCompanyUser(newCompanyUserVo);
    }

    private NewCompanyUserVo convert(NewCompanyUserRequest newCompanyUserRequest){
        NewCompanyUserVo newCompanyUserVo = new NewCompanyUserVo();
        newCompanyUserVo.setCompanyName(newCompanyUserRequest.getCompanyName());
        newCompanyUserVo.setCompanyStatus(newCompanyUserRequest.getCompanyStatus());
        //newCompanyUserVo.setUserStatus(newCompanyUserRequest.getUserStatus());
        newCompanyUserVo.setType(newCompanyUserRequest.getType());
        newCompanyUserVo.setName(newCompanyUserRequest.getName());
        newCompanyUserVo.setSurname(newCompanyUserRequest.getSurname());
        newCompanyUserVo.setBirthdate(newCompanyUserRequest.getBirthdate());
        newCompanyUserVo.setTelNo(newCompanyUserRequest.getTelNo());
        newCompanyUserVo.setEmail(newCompanyUserRequest.getEmail());
        newCompanyUserVo.setPassword(newCompanyUserRequest.getPassword());
        return newCompanyUserVo;
    }


    public WhoAmIDTO whoAmI() {
        return companyUserService.whoAmI();
    }

    public List<PlaceIdDTO> getAllPlaceByCompanyId(GetAllPlaceRequest getAllPlaceRequest) {
        return companyUserService.getAllPlaceByCompanyId(getAllPlaceRequest.getCompanyId());
    }
}
