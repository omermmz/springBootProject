package com.example.demo.controller;

import com.example.demo.manager.CompanyUserManager;
import com.example.demo.model.dto.CompanyUserIdDTO;
import com.example.demo.model.request.NewCompanyUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/companyuser")
@RequiredArgsConstructor
public class CompanyUserController {
    private final CompanyUserManager companyUserManager;

    @GetMapping(path = "{type}")
    public List<CompanyUserIdDTO> getUsersByType(@PathVariable String type){
        return companyUserManager.getUsersByType(type);
    }

    @PostMapping
    public void registerNewCompanyUser(@RequestBody NewCompanyUserRequest newCompanyUserRequest){
        companyUserManager.initializeCompanyUser(newCompanyUserRequest);
    }
}
