package com.example.wordsjourney.Services;

import com.example.wordsjourney.Models.Dtos.userDTO;
import com.example.wordsjourney.Models.Entity.userEntity;

public interface signupINT {

    boolean saveUser(userDTO user);
    boolean existingUserByEmailAndPassword(String email , String password);
    boolean existsByEmail(String email);
    boolean updateUser(String email, String name, String picture);
}
