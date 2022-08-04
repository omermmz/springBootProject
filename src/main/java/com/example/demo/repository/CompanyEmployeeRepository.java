package com.example.demo.repository;

import com.example.demo.model.entity.CompanyEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyEmployeeRepository extends JpaRepository<CompanyEmployee,Long> {
    CompanyEmployee findCompanyEmployeeByUserId(Long id);
}
