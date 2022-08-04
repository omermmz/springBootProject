package com.example.demo.service;

import com.example.demo.model.dto.UserInfoDTO;
import com.example.demo.model.entity.UserPersonalInfo;
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


    public UserInfoDTO addNewUserInfo(NewUserInfoVo newUserInfoVo) {
        UserPersonalInfo userPersonalInfo = convert(newUserInfoVo);
        userPersonalInfo = userPersonalInfoRepository.save(userPersonalInfo);

        return convert(userPersonalInfo);
    }
    private UserPersonalInfo convert(NewUserInfoVo newUserInfoVo){
        UserPersonalInfo userPersonalInfo = new UserPersonalInfo();
        userPersonalInfo.setUser_id(newUserInfoVo.getUserId());
        userPersonalInfo.setType(newUserInfoVo.getType());
        userPersonalInfo.setName(newUserInfoVo.getName());
        userPersonalInfo.setSurname(newUserInfoVo.getSurname());
        userPersonalInfo.setBirthdate(newUserInfoVo.getBirthdate());
        userPersonalInfo.setPhone_number(newUserInfoVo.getTelNo());
        return userPersonalInfo;
    }
    private UserInfoDTO convert(UserPersonalInfo userPersonalInfo){
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userPersonalInfo.getId());
        userInfoDTO.setUserId(userPersonalInfo.getUser_id());
        userInfoDTO.setType(userPersonalInfo.getType());
        userInfoDTO.setName(userPersonalInfo.getName());
        userInfoDTO.setSurname(userPersonalInfo.getSurname());
        userInfoDTO.setBirthdate(userPersonalInfo.getBirthdate());
        userInfoDTO.setPhoneNumber(userPersonalInfo.getPhone_number());
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
            userInfoDTO.setUserId(userPersonals.getUser_id());
            userInfoDTO.setType(userPersonals.getType());
            userInfoDTO.setName(userPersonals.getName());
            userInfoDTO.setSurname(userPersonals.getSurname());
            userInfoDTO.setBirthdate(userPersonals.getBirthdate());
            userInfoDTO.setPhoneNumber(userPersonals.getPhone_number());
            userInfoDTOs.add(userInfoDTO);
        }
        return userInfoDTOs;
    }
}
