package com.example.demo.repository;

import com.example.demo.model.entity.PlaceField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceField,Long> {
    List<PlaceField> getAllByCompanyId(Long companyId);
}
