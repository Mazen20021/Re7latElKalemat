package com.example.wordsjourney.Repos;

import com.example.wordsjourney.Models.Entity.userEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface signupRepoINT extends JpaRepository<userEntity, Long> {
    boolean existsByEmailAndPassword(String email , String pass);
    boolean existsByEmail(String email);
    userEntity findByEmail(String email);
}
