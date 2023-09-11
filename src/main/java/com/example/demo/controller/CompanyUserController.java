package com.example.demo.controller;

import com.example.demo.manager.CompanyUserManager;
import com.example.demo.model.dto.CompanyUserIdDTO;
import com.example.demo.model.dto.PlaceIdDTO;
import com.example.demo.model.dto.ReservationDTO;
import com.example.demo.model.dto.WhoAmIDTO;
import com.example.demo.model.request.GetAllPlaceRequest;
import com.example.demo.model.request.NewCompanyUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/companyuser")
@RequiredArgsConstructor
public class CompanyUserController {
    private final CompanyUserManager companyUserManager;



    @PostMapping
    public void registerNewCompanyUser(@RequestBody NewCompanyUserRequest newCompanyUserRequest){
        companyUserManager.initializeCompanyUser(newCompanyUserRequest);
    }

    @PostMapping(path = "/getAllPlace")
    public List<PlaceIdDTO> getAllPlaceByCompanyId(@RequestBody GetAllPlaceRequest getAllPlaceRequest){
       return companyUserManager.getAllPlaceByCompanyId(getAllPlaceRequest);
    }




    @ResponseBody
    @GetMapping(path = "/whoAmI")
    public WhoAmIDTO whoAmI() {
        return companyUserManager.whoAmI();
    }

}

