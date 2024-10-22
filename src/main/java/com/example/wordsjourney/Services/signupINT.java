package com.example.wordsjourney.Services;

import com.example.wordsjourney.Models.Dtos.userDTO;

public interface signupINT {
    boolean saveUser(userDTO user);
    boolean existingUserByEmailAndPassword(String email , String password);
}
