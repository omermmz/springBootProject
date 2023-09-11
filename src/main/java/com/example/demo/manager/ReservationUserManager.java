package com.example.demo.manager;

import com.example.demo.model.dto.CompanyUserIdDTO;
import com.example.demo.model.dto.ReservationUserIdDTO;
import com.example.demo.model.request.NewReservationUserRequest;
import com.example.demo.model.vo.NewReservationUserVo;
import com.example.demo.service.ReservationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationUserManager {
    private final ReservationUserService reservationUserService;

    public void initializeReservUser(NewReservationUserRequest newReservationUserRequest) {
        NewReservationUserVo newReservationUserVo = convert(newReservationUserRequest);
        reservationUserService.addNewReservUser(newReservationUserVo);
    }

    private NewReservationUserVo convert(NewReservationUserRequest newReservationUserRequest){
        NewReservationUserVo newReservationUserVo = new NewReservationUserVo();
        newReservationUserVo.setStatus(newReservationUserRequest.getStatus());
        newReservationUserVo.setType(newReservationUserRequest.getType());
        newReservationUserVo.setName(newReservationUserRequest.getName());
        newReservationUserVo.setSurname(newReservationUserRequest.getSurname());
        newReservationUserVo.setBirthdate(newReservationUserRequest.getBirthdate());
        newReservationUserVo.setPhoneNumber(newReservationUserRequest.getPhoneNumber());
        newReservationUserVo.setEmail(newReservationUserRequest.getEmail());
        newReservationUserVo.setPassword(newReservationUserRequest.getPassword());
        return newReservationUserVo;
    }


}
