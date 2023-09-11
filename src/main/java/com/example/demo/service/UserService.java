package com.example.demo.service;

import com.example.demo.model.dto.*;
import com.example.demo.model.request.UpdateMailRequest;
import com.example.demo.model.request.UpdatePasswordRequest;
import com.example.demo.model.request.UpdateUserInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {


    private final CompanyUserService companyUserService;
    private final ReservationUserService reservationUserService;

    private final UserCredentialService userCredentialService;

    private final UserInfoService userInfoService;
    public List<CompanyUserIdDTO> getCompanyUsersByType(String type) {
        return companyUserService.getUsersByType(type);
    }

    public List<ReservationUserIdDTO> getReservationUsersByType(String type) {
        return reservationUserService.getUsersByType(type);
    }

    public WhoAmIDTO whoAmI() {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) auth.getPrincipal();
        WhoAmIDTO whoAmIDTO = userCredentialService.getUserCredentialWithRoles(userId);
        return whoAmIDTO;

    }

    public UserInfoDTO getUserInfo() {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) auth.getPrincipal();
        UserInfoDTO userInfoDTO = userInfoService.findUserInfoByUserId(userId);
        return userInfoDTO;
    }

    public String updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) auth.getPrincipal();
        return userCredentialService.updatePassword(userId,updatePasswordRequest.getLastPassword(),updatePasswordRequest.getNewPassword());

    }

    public String updateMail(UpdateMailRequest updateMailRequest) {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) auth.getPrincipal();
        return userCredentialService.updateMail(userId,updateMailRequest.getNewMail());
    }

    public void updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest) {
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        Long userId = (Long) auth.getPrincipal();
        userInfoService.updateUserInfo(userId,updateUserInfoRequest);
    }
}
