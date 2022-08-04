package com.example.demo.service;

import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.NewCompanyUserVo;
import com.example.demo.model.vo.NewReservationUserVo;
import com.example.demo.model.vo.NewUserCredentialVo;
import com.example.demo.model.vo.NewUserInfoVo;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationUserService {
    private final UserRepository userRepository;
    private final UserInfoService userInfoService;
    private final UserCredentialService userCredentialService;

    public UserDTO addNewReservUser(NewReservationUserVo newReservationUserVo) {
        User user = convert(newReservationUserVo);
        user = userRepository.save(user);

        NewUserInfoVo newUserInfoVo = convertUserInfo(newReservationUserVo);
        newUserInfoVo.setUserId(user.getId());
        userInfoService.addNewUserInfo(newUserInfoVo);

        NewUserCredentialVo newUserCredentialVo = convertUserCredential(newReservationUserVo);
        newUserCredentialVo.setUserId(user.getId());
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
}
