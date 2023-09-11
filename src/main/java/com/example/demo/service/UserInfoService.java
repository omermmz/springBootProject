package com.example.demo.service;

import com.example.demo.model.dto.CompanyDTO;
import com.example.demo.model.dto.UserInfoDTO;
import com.example.demo.model.entity.UserPersonalInfo;
import com.example.demo.model.request.UpdateUserInfoRequest;
import com.example.demo.model.vo.NewUserInfoVo;
import com.example.demo.repository.UserPersonalInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserPersonalInfoRepository userPersonalInfoRepository;
    private final CompanyService companyService;


    public UserInfoDTO addNewUserInfo(NewUserInfoVo newUserInfoVo) {
        UserPersonalInfo userPersonalInfo = convert(newUserInfoVo);
        userPersonalInfo = userPersonalInfoRepository.save(userPersonalInfo);

        return convert(userPersonalInfo);
    }

    public UserInfoDTO findUserInfoByUserId(Long id) {
        UserPersonalInfo userPersonalInfo = userPersonalInfoRepository.getByUserId(id).orElseThrow(() -> new IllegalArgumentException("user info doesn't exist"));
        return convert(userPersonalInfo);
    }
    private UserPersonalInfo convert(NewUserInfoVo newUserInfoVo){
        UserPersonalInfo userPersonalInfo = new UserPersonalInfo();
        userPersonalInfo.setUserId(newUserInfoVo.getUserId());
        userPersonalInfo.setType(newUserInfoVo.getType());
        userPersonalInfo.setName(newUserInfoVo.getName());
        userPersonalInfo.setSurname(newUserInfoVo.getSurname());
        userPersonalInfo.setBirthdate(newUserInfoVo.getBirthdate());
        userPersonalInfo.setPhoneNumber(newUserInfoVo.getTelNo());
        return userPersonalInfo;
    }
    private UserInfoDTO convert(UserPersonalInfo userPersonalInfo){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        if(userPersonalInfo.getType().equals("Company Employee")){
            userInfoDTO.setCompanyName(companyService.getCompanyNameById(userPersonalInfo.getUserId()));
        }
        userInfoDTO.setId(userPersonalInfo.getId());
        userInfoDTO.setUserId(userPersonalInfo.getUserId());
        userInfoDTO.setType(userPersonalInfo.getType());
        userInfoDTO.setName(userPersonalInfo.getName());
        userInfoDTO.setSurname(userPersonalInfo.getSurname());
        userInfoDTO.setBirthdate(userPersonalInfo.getBirthdate());
        userInfoDTO.setPhoneNumber(userPersonalInfo.getPhoneNumber());
        return userInfoDTO;
    }


    public List<UserInfoDTO> getUsersByType(String type) {
        return convertListDTO(userPersonalInfoRepository.findUserPersonalInfoByType(type));
    }

    private List<UserInfoDTO> convertListDTO(List<UserPersonalInfo> userPersonalInfoList){
        List<UserInfoDTO> userInfoDTOs = new ArrayList<>();
        for (UserPersonalInfo userPersonals:userPersonalInfoList) {
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setId(userPersonals.getId());
            userInfoDTO.setUserId(userPersonals.getUserId());
            userInfoDTO.setType(userPersonals.getType());
            userInfoDTO.setName(userPersonals.getName());
            userInfoDTO.setSurname(userPersonals.getSurname());
            userInfoDTO.setBirthdate(userPersonals.getBirthdate());
            userInfoDTO.setPhoneNumber(userPersonals.getPhoneNumber());
            userInfoDTOs.add(userInfoDTO);
        }
        return userInfoDTOs;
    }



    public void updateUserInfo(Long userId, UpdateUserInfoRequest updateUserInfoRequest) {
        UserPersonalInfo userPersonalInfo = userPersonalInfoRepository.getByUserId(userId).orElseThrow(() -> new IllegalArgumentException("user info doesn't exist"));
        if(userPersonalInfo.getType().equals("Company Employee")){
            CompanyDTO companyDTO = companyService.updateCompanyName(updateUserInfoRequest.getCompanyName(), userId);
        }
        update(userPersonalInfo,updateUserInfoRequest);
        userPersonalInfoRepository.save(userPersonalInfo);


    }

    private void update(UserPersonalInfo userPersonalInfo,UpdateUserInfoRequest updateUserInfoRequest){
            userPersonalInfo.setName(updateUserInfoRequest.getUserName());
            userPersonalInfo.setSurname(updateUserInfoRequest.getUserSurname());
            userPersonalInfo.setBirthdate(updateUserInfoRequest.getBirthdate());
            userPersonalInfo.setPhoneNumber(updateUserInfoRequest.getPhoneNumber());
    }
}
