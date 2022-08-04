package com.example.demo.service;

import com.example.demo.model.dto.UserCredentialDTO;
import com.example.demo.model.entity.UserCredential;
import com.example.demo.model.vo.NewUserCredentialVo;
import com.example.demo.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserCredentialService {
    private final UserCredentialRepository userCredentialRepository;

    public UserCredentialDTO addNewUserCredential(NewUserCredentialVo newUserCredentialVo) {
        UserCredential userCredential = convert(newUserCredentialVo);
        userCredential = userCredentialRepository.save(userCredential);
        return convert(userCredential);
    }

    private UserCredential convert(NewUserCredentialVo newUserCredentialVo){
        UserCredential userCredential = new UserCredential();
        userCredential.setUserId(newUserCredentialVo.getUserId());
        userCredential.setEmail(newUserCredentialVo.getEmail());
        userCredential.setPassword(newUserCredentialVo.getPassword());
        return userCredential;
    }
    private UserCredentialDTO convert(UserCredential userCredential){
        UserCredentialDTO userCredentialDTO = new UserCredentialDTO();
        userCredentialDTO.setId(userCredential.getId());
        userCredentialDTO.setUserId(userCredential.getUserId());
        userCredentialDTO.setEmail(userCredential.getEmail());
        userCredentialDTO.setPassword(userCredential.getPassword());
        return userCredentialDTO;
    }

    public UserCredentialDTO getCredentialById(Long id) {
        UserCredential userCredential = userCredentialRepository.findUserCredentialByUserId(id);
        return convert(userCredential);
    }
}
