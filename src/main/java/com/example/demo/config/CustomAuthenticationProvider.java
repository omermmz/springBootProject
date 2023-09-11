package com.example.demo.config;

import com.example.demo.model.dto.RoleDTO;
import com.example.demo.model.dto.UserCredentialDTO;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.model.entity.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CompanyUserService;
import com.example.demo.service.ReservationUserService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserCredentialService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserCredentialService userCredentialService;
    private final CompanyUserService companyUserService;
    private final ReservationUserService reservationUserService;
    private final RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserCredentialDTO userCredentialDTO = userCredentialService.getCredentialByEmail(email);
        UserDTO userDTO = companyUserService.getUserById(userCredentialDTO.getUserId());
        UserDTO userDTO1 = reservationUserService.getUserById(userCredentialDTO.getUserId());
        if(!bCryptPasswordEncoder.matches(password,userCredentialDTO.getPassword())){
            throw new BadCredentialsException("wrong password");
        }

/*
        if(userCredentialDTO.getRoleId()==2){
            if(userDTO.getStatus()!="Active"){
                throw new BadCredentialsException("user not active");
            }
        }
        if(userCredentialDTO.getRoleId()==3){
            if(userDTO1.getStatus()!="Active"){
                throw new BadCredentialsException("user not active");
            }
        }
*/
        RoleDTO roleDTO = roleService.getRoles(userCredentialDTO.getRoleId());
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();


        //for (RoleDTO role:roles){
            GrantedAuthority grantedAuthority = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return roleDTO.getRole();
                }

            };
            grantedAuthorities.add(grantedAuthority);
        //}

        return new UsernamePasswordAuthenticationToken(userCredentialDTO.getUserId(), password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
