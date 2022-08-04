package com.example.demo.service;

import com.example.demo.model.dto.*;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.*;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyUserService {
    private final UserRepository userRepository;
    private final CompanyService companyService;
    private final UserInfoService userInfoService;
    private final UserCredentialService userCredentialService;
    private final CompanyEmployeeService companyEmployeeService;

    public UserDTO addNewCompanyUser(NewCompanyUserVo newCompanyUserVo) {
        NewCompanyVo newCompanyVo = convertCompany(newCompanyUserVo);
        CompanyDTO companyDTO = companyService.addNewCompany(newCompanyVo);

        User user1 = convert(newCompanyUserVo);
        user1 = userRepository.save(user1);

        NewUserInfoVo newUserInfoVo = convertUserInfo(newCompanyUserVo);
        newUserInfoVo.setUserId(user1.getId());
        userInfoService.addNewUserInfo(newUserInfoVo);

        NewUserCredentialVo newUserCredentialVo = convertUserCredential(newCompanyUserVo);
        newUserCredentialVo.setUserId(user1.getId());
        userCredentialService.addNewUserCredential(newUserCredentialVo);

        NewCompanyEmployeeVo newCompanyEmployeeVo = new NewCompanyEmployeeVo();
        newCompanyEmployeeVo.setUserId(user1.getId());
        newCompanyEmployeeVo.setCompanyId(companyDTO.getId());
        companyEmployeeService.addNewEmployee(newCompanyEmployeeVo);
        return convert(user1);
    }

    private NewCompanyVo convertCompany(NewCompanyUserVo newCompanyUserVo){
        NewCompanyVo newCompanyVo = new NewCompanyVo();
        newCompanyVo.setStatus(newCompanyUserVo.getCompanyStatus());
        newCompanyVo.setName(newCompanyUserVo.getCompanyName());
        return newCompanyVo;
    }
    private User convert(NewCompanyUserVo newCompanyUserVo){
        User user = new User();
        user.setStatus(newCompanyUserVo.getUserStatus());
        return user;
    }
    private NewUserInfoVo convertUserInfo(NewCompanyUserVo newCompanyUserVo){
        NewUserInfoVo newUserInfoVo = new NewUserInfoVo();
        newUserInfoVo.setType(newCompanyUserVo.getType());
        newUserInfoVo.setName(newCompanyUserVo.getName());
        newUserInfoVo.setSurname(newCompanyUserVo.getSurname());
        newUserInfoVo.setBirthdate(newCompanyUserVo.getBirthdate());
        newUserInfoVo.setTelNo(newCompanyUserVo.getTelNo());
        return newUserInfoVo;
    }
    private NewUserCredentialVo convertUserCredential(NewCompanyUserVo newCompanyUserVo){
        NewUserCredentialVo newUserCredentialVo = new NewUserCredentialVo();
        newUserCredentialVo.setEmail(newCompanyUserVo.getEmail());
        newUserCredentialVo.setPassword(newCompanyUserVo.getPassword());
        return newUserCredentialVo;
    }
    private UserDTO convert(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setStatus(user.getStatus());
        userDTO.setCreateDate(user.getCreate_date());
        userDTO.setUpdateDate(user.getUpdate_date());
        return userDTO;
    }

    public List<CompanyUserIdDTO> getUsersByType(String type) {
        List<UserInfoDTO> userInfoDTO = userInfoService.getUsersByType(type);
        List<CompanyUserIdDTO> companyUserIdDTO = fillCompanyUserIdDTO(userInfoDTO);
        companyUserIdDTO = fillCompanyUserIdDTOWithCompanyName(companyUserIdDTO);
        companyUserIdDTO = fillCompanyUserIdDTOWithCredential(companyUserIdDTO);

        return  companyUserIdDTO;
    }

    private List<CompanyUserIdDTO> fillCompanyUserIdDTOWithCredential(List<CompanyUserIdDTO> companyUserIdDTO) {
        for (CompanyUserIdDTO companyUserIdDTO1:companyUserIdDTO) {
            UserCredentialDTO userCredentialDTO = userCredentialService.getCredentialById(companyUserIdDTO1.getUserId());
            companyUserIdDTO1.setEmail(userCredentialDTO.getEmail());
            companyUserIdDTO1.setPassword(userCredentialDTO.getPassword());
        }
        return companyUserIdDTO;
    }

    private List<CompanyUserIdDTO> fillCompanyUserIdDTO(List<UserInfoDTO> userInfoDTOs){
        List<CompanyUserIdDTO> companyUserIdDTOList = new ArrayList<>();
        for(UserInfoDTO userInfoDTO:userInfoDTOs){
            User user = userRepository.findUserById(userInfoDTO.getUserId());
            UserDTO userDTO = convert(user);
            CompanyUserIdDTO companyUserIdDTO = convertDTOs(userDTO,userInfoDTO);
            companyUserIdDTOList.add(companyUserIdDTO);
        }
        return companyUserIdDTOList;
    }
    private CompanyUserIdDTO convertDTOs(UserDTO userDTO,UserInfoDTO userInfoDTO){
        CompanyUserIdDTO companyUserIdDTO = new CompanyUserIdDTO();
        companyUserIdDTO.setUserId(userInfoDTO.getUserId());
        companyUserIdDTO.setStatus(userDTO.getStatus());
        companyUserIdDTO.setCreateDate(userDTO.getCreateDate());
        companyUserIdDTO.setUpdateDate(userDTO.getUpdateDate());
        companyUserIdDTO.setType(userInfoDTO.getType());
        companyUserIdDTO.setName(userInfoDTO.getName());
        companyUserIdDTO.setSurname(userInfoDTO.getSurname());
        companyUserIdDTO.setBirthdate(userInfoDTO.getBirthdate());
        companyUserIdDTO.setTelNo(userInfoDTO.getPhoneNumber());
        return companyUserIdDTO;
    }

    private List<CompanyUserIdDTO> fillCompanyUserIdDTOWithCompanyName(List<CompanyUserIdDTO> companyUserIdDTOList){
        for (CompanyUserIdDTO companyUserIdDTO:companyUserIdDTOList) {
            companyUserIdDTO.setCompanyName(companyService.getCompanyNameById(companyUserIdDTO.getUserId()));
        }
        return companyUserIdDTOList;
    }
}
