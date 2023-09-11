package com.example.demo.service;

import com.example.demo.model.dto.*;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.NewCompanyUserVo;
import com.example.demo.model.vo.NewReservationUserVo;
import com.example.demo.model.vo.NewUserCredentialVo;
import com.example.demo.model.vo.NewUserInfoVo;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationUserService {
    private final UserRepository userRepository;
    private final UserInfoService userInfoService;
    private final UserCredentialService userCredentialService;
    private final RoleService roleService;

    public UserDTO addNewReservUser(NewReservationUserVo newReservationUserVo) {
        User user = convert(newReservationUserVo);
        user = userRepository.save(user);

        NewUserInfoVo newUserInfoVo = convertUserInfo(newReservationUserVo);
        newUserInfoVo.setUserId(user.getId());
        userInfoService.addNewUserInfo(newUserInfoVo);

        NewUserCredentialVo newUserCredentialVo = convertUserCredential(newReservationUserVo);
        newUserCredentialVo.setUserId(user.getId());
        RoleDTO roleDTO = roleService.findRole("USER");
        newUserCredentialVo.setRoleId(roleDTO.getId());
        userCredentialService.addNewUserCredential(newUserCredentialVo);
        return convert(user);
    }

    private User convert(NewReservationUserVo newReservationUserVo){
        User user = new User();
        user.setStatus(newReservationUserVo.getStatus());
        return user;
    }
    private NewUserInfoVo convertUserInfo(NewReservationUserVo newReservationUserVo){
        NewUserInfoVo newUserInfoVo = new NewUserInfoVo();
        newUserInfoVo.setType(newReservationUserVo.getType());
        newUserInfoVo.setName(newReservationUserVo.getName());
        newUserInfoVo.setSurname(newReservationUserVo.getSurname());
        newUserInfoVo.setBirthdate(newReservationUserVo.getBirthdate());
        newUserInfoVo.setTelNo(newReservationUserVo.getPhoneNumber());
        return newUserInfoVo;
    }
    private NewUserCredentialVo convertUserCredential(NewReservationUserVo newReservationUserVo){
        NewUserCredentialVo newUserCredentialVo = new NewUserCredentialVo();
        newUserCredentialVo.setEmail(newReservationUserVo.getEmail());
        newUserCredentialVo.setPassword(newReservationUserVo.getPassword());
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

    public List<ReservationUserIdDTO> getUsersByType(String type) {
        List<UserInfoDTO> userInfoDTO = userInfoService.getUsersByType(type);
        List<ReservationUserIdDTO> reservationUserIdDTO = fillCompanyUserIdDTO(userInfoDTO);
        reservationUserIdDTO = fillCompanyUserIdDTOWithCredential(reservationUserIdDTO);

        return  reservationUserIdDTO;
    }

    private List<ReservationUserIdDTO> fillCompanyUserIdDTOWithCredential(List<ReservationUserIdDTO> reservationUserIdDTOs) {
        for (ReservationUserIdDTO reservationUserIdDTO:reservationUserIdDTOs) {
            UserCredentialDTO userCredentialDTO = userCredentialService.getCredentialById(reservationUserIdDTO.getUserId());
            reservationUserIdDTO.setEmail(userCredentialDTO.getEmail());
            reservationUserIdDTO.setPassword(userCredentialDTO.getPassword());

        }
        return reservationUserIdDTOs;
    }

    private List<ReservationUserIdDTO> fillCompanyUserIdDTO(List<UserInfoDTO> userInfoDTOs) {
        List<ReservationUserIdDTO> reservationUserIdDTOList = new ArrayList<>();
        for(UserInfoDTO userInfoDTO:userInfoDTOs){
            User user = userRepository.findUserById(userInfoDTO.getUserId());
            UserDTO userDTO = convert(user);
            ReservationUserIdDTO reservationUserIdDTO = convertDTOs(userDTO,userInfoDTO);
            reservationUserIdDTOList.add(reservationUserIdDTO);
        }
        return reservationUserIdDTOList;
    }

    private ReservationUserIdDTO convertDTOs(UserDTO userDTO, UserInfoDTO userInfoDTO) {
        ReservationUserIdDTO reservationUserIdDTO = new ReservationUserIdDTO();
        reservationUserIdDTO.setUserId(userInfoDTO.getUserId());
        reservationUserIdDTO.setStatus(userDTO.getStatus());
        reservationUserIdDTO.setCreateDate(userDTO.getCreateDate());
        reservationUserIdDTO.setUpdateDate(userDTO.getUpdateDate());
        reservationUserIdDTO.setType(userInfoDTO.getType());
        reservationUserIdDTO.setName(userInfoDTO.getName());
        reservationUserIdDTO.setSurname(userInfoDTO.getSurname());
        reservationUserIdDTO.setBirthdate(userInfoDTO.getBirthdate());
        reservationUserIdDTO.setTelNo(userInfoDTO.getPhoneNumber());
        return reservationUserIdDTO;
    }
    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User id couldn't found"));
        return convert(user);
    }


}
