package com.example.demo.repository;

import com.example.demo.model.entity.UserPersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPersonalInfoRepository extends JpaRepository<UserPersonalInfo,Long> {
    List<UserPersonalInfo> findUserPersonalInfoByType(String type);
}
