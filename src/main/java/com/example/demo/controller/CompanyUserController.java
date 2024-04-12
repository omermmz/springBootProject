package com.example.demo.controller;

import com.example.demo.manager.CompanyUserManager;
import com.example.demo.model.dto.*;
import com.example.demo.model.request.GetAllPlaceRequest;
import com.example.demo.model.request.NewCompanyUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/companyuser")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001", allowCredentials = "true", allowedHeaders = "Access-Control-Allow-Headers,Access-Control-Allow-Origin,Access-Control-Request-Method, Access-Control-Request-Headers,Origin,Cache-Control, Content-Type, Authorization, Access-Control-Allow-Methods")
public class CompanyUserController {
    private final CompanyUserManager companyUserManager;



    @PostMapping
    public UserDTO registerNewCompanyUser(@RequestBody NewCompanyUserRequest newCompanyUserRequest){
        return companyUserManager.initializeCompanyUser(newCompanyUserRequest);
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

