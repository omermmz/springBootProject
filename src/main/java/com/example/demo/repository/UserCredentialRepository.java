package com.example.demo.repository;

import com.example.demo.model.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,Long> {
   // UserCredential findUserCredentialByUserId(Long id);

    Optional<UserCredential> findUserCredentialByUserId(Long id);

    Optional<UserCredential> findUserCredentialByEmail(String email);
}
