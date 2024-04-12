package com.example.demo.service;

import com.example.demo.model.dto.*;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.model.vo.*;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyUserService {
    private final UserRepository userRepository;
    private final CompanyService companyService;
    private final UserInfoService userInfoService;
    private final UserCredentialService userCredentialService;
    private final CompanyEmployeeService companyEmployeeService;
    private final RoleService roleService;

    private final PlaceService placeService;


    public UserDTO addNewCompanyUser(NewCompanyUserVo newCompanyUserVo) {
        NewCompanyVo newCompanyVo = convertCompany(newCompanyUserVo);
        CompanyDTO companyDTO = companyService.addNewCompany(newCompanyVo);

        User user1 = convert();
        user1 = userRepository.save(user1);

        addCompanyCredentialAndCompanyEmployee(newCompanyUserVo,user1,companyDTO);

        NewUserInfoVo newUserInfoVo = convertUserInfo(newCompanyUserVo);
        newUserInfoVo.setUserId(user1.getId());
        userInfoService.addNewUserInfo(newUserInfoVo);

        return convert(user1);
    }
    private void addCompanyCredentialAndCompanyEmployee(NewCompanyUserVo newCompanyUserVo, User user1,CompanyDTO companyDTO){
        NewUserCredentialVo newUserCredentialVo = convertUserCredential(newCompanyUserVo);
        RoleDTO roleDTO = roleService.findRole("COMPANYUSER");
        newUserCredentialVo.setUserId(user1.getId());
        newUserCredentialVo.setRoleId(roleDTO.getId());
        userCredentialService.addNewUserCredential(newUserCredentialVo);

        NewCompanyEmployeeVo newCompanyEmployeeVo = new NewCompanyEmployeeVo();
        newCompanyEmployeeVo.setUserId(user1.getId());
        newCompanyEmployeeVo.setCompanyId(companyDTO.getId());
        companyEmployeeService.addNewEmployee(newCompanyEmployeeVo);
    }

    public List<PlaceIdDTO> getAllPlaceByCompanyId(Long companyId) {
        return placeService.getAllPlaceByCompanyId(companyId);

    }

    private NewCompanyVo convertCompany(NewCompanyUserVo newCompanyUserVo){
        NewCompanyVo newCompanyVo = new NewCompanyVo();
        //newCompanyVo.setStatus(newCompanyUserVo.getCompanyStatus());
        newCompanyVo.setName(newCompanyUserVo.getCompanyName());
        return newCompanyVo;
    }
    private User convert(){
        User user = new User();
        user.setStatus("Active");
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
            RoleDTO roleDTO = roleService.getRoles(userCredentialDTO.getRoleId());
            companyUserIdDTO1.setRoles(roleDTO.getRole());
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
    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("User id couldn't found"));
        return convert(user);
    }

        public WhoAmIDTO whoAmI() {
            AbstractAuthenticationToken auth = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

            Long userId = (Long) auth.getPrincipal();
            WhoAmIDTO whoAmIDTO = userCredentialService.getUserCredentialWithRoles(userId);
            return whoAmIDTO;

        }


}
