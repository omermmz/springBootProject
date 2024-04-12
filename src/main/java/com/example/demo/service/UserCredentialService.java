package com.example.demo.service;


import ch.qos.logback.core.status.Status;
import com.example.demo.model.dto.*;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.UserCredential;
import com.example.demo.model.entity.UserPersonalInfo;
import com.example.demo.model.request.UpdateUserInfoRequest;
import com.example.demo.model.vo.NewUserCredentialVo;
import com.example.demo.repository.CompanyEmployeeRepository;
import com.example.demo.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;
    private final UserInfoService userInfoService;
    private final RoleService roleService;
    private final CompanyEmployeeService companyEmployeeService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserCredentialDTO addNewUserCredential(NewUserCredentialVo newUserCredentialVo) {
        userCredentialIsExist(newUserCredentialVo.getEmail());
        UserCredential userCredential = convert(newUserCredentialVo);
        userCredential = userCredentialRepository.saveAndFlush(userCredential);
        return convert(userCredential);
    }

    private UserCredential convert(NewUserCredentialVo newUserCredentialVo){
        UserCredential userCredential = new UserCredential();
        userCredential.setUserId(newUserCredentialVo.getUserId());
        userCredential.setEmail(newUserCredentialVo.getEmail());
        userCredential.setPassword(bCryptPasswordEncoder.encode(newUserCredentialVo.getPassword()));

        userCredential.setRolesId(newUserCredentialVo.getRoleId());
        return userCredential;
    }
    private UserCredentialDTO convert(UserCredential userCredential){
        UserCredentialDTO userCredentialDTO = new UserCredentialDTO();
        userCredentialDTO.setId(userCredential.getId());
        userCredentialDTO.setUserId(userCredential.getUserId());
        userCredentialDTO.setEmail(userCredential.getEmail());
        userCredentialDTO.setPassword(userCredential.getPassword());
        userCredentialDTO.setRoleId(userCredential.getRolesId());
        return userCredentialDTO;
    }

    public UserCredentialDTO getCredentialById(Long id) {
        UserCredential userCredential = userCredentialRepository.findUserCredentialByUserId(id).orElseThrow(() -> new UsernameNotFoundException("User is not exists"));
        return convert(userCredential);
    }

    public UserCredentialDTO getCredentialByEmail(String email){
        UserCredential userCredential = userCredentialRepository.findUserCredentialByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User is not exists"));
        return convert(userCredential);
    }

    public void userCredentialIsExist(String email){
        if(userCredentialRepository.findUserCredentialByEmail(email).isPresent()){
            throw new IllegalArgumentException("email taken");
        }
    }


    public WhoAmIDTO getUserCredentialWithRoles(Long id) {
        CompanyEmployeeDTO companyEmployeeDTO=new CompanyEmployeeDTO();
        UserCredential userCredential = userCredentialRepository.findUserCredentialByUserId(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        UserInfoDTO userInfoDTO = userInfoService.findUserInfoByUserId(id);

        if(userInfoDTO.getType().equals("Company Employee")){
          companyEmployeeDTO  = companyEmployeeService.getCompanyIdByUserId(id);
        }
        WhoAmIDTO whoAmIDTO = convertToWDTO(userCredential,userInfoDTO);
        RoleDTO roleDTO = roleService.getRoles(userCredential.getRolesId());
        whoAmIDTO.setRole(roleDTO.getRole());
        whoAmIDTO.setCompanyId(companyEmployeeDTO.getCompanyId());
        return whoAmIDTO;

    }

    private WhoAmIDTO convertToWDTO(UserCredential userCredential,UserInfoDTO userInfoDTO) {
        WhoAmIDTO whoAmIDTO = new WhoAmIDTO();
        whoAmIDTO.setUserId(userCredential.getUserId());
        whoAmIDTO.setUserName(userInfoDTO.getName());
        whoAmIDTO.setUserSurname(userInfoDTO.getSurname());
        whoAmIDTO.setUserType(userInfoDTO.getType());
        return whoAmIDTO;
    }


    public String updatePassword(Long userId, String lastPassword, String newPassword) {
        UserCredential userCredential = userCredentialRepository.findUserCredentialByUserId(userId).orElseThrow(() ->new UsernameNotFoundException("User is not exists") );
        boolean isMatch = bCryptPasswordEncoder.matches(lastPassword,userCredential.getPassword());
        if(isMatch!=true){
       //     var httpHeaders = new HttpHeaders();
         //   httpHeaders.setContentType(new MediaType("text", "html", StandardCharsets.UTF_8));
          //   new ResponseEntity<>("Sifreler Aynı Degil", httpHeaders, HttpStatus.METHOD_NOT_ALLOWED);
            return "Uyusmazlik";
        }else {
            userCredential.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userCredentialRepository.save(userCredential);
     //       var httpHeaders = new HttpHeaders();
      //      httpHeaders.setContentType(new MediaType("text", "html", StandardCharsets.UTF_8));
            //new ResponseEntity<>("Başarılı", httpHeaders, HttpStatus.OK);
            return "oldu";
        }
    }

    public String updateMail(Long userId, String newMail) {
        UserCredential userCredential = userCredentialRepository.findUserCredentialByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("User is not exists"));
        userCredentialIsExist(newMail);
        userCredential.setEmail(newMail);
        userCredentialRepository.save(userCredential);
        return "Successfully updated";
    }


}
