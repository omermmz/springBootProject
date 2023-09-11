package com.example.demo.service;

import com.example.demo.model.dto.RoleDTO;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.UserCredential;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleDTO findRole(String roleParam){
        Role role = roleRepository.findRoleByRole(roleParam).orElseThrow(()-> new IllegalArgumentException("Role couldn't find"));
        RoleDTO roleDTO = convert(role);
        return roleDTO;
    }
    public RoleDTO getRoles(Long id){

            Role role = roleRepository.findRoleById(id).orElseThrow(()->new UsernameNotFoundException("role couldn't found"));
            RoleDTO roleDTO = convert(role);

        return roleDTO;
    }


    private RoleDTO convert(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());
        return roleDTO;
    }
}
