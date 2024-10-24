package com.example.wordsjourney.Services;

import com.example.wordsjourney.Models.Dtos.userDTO;
import com.example.wordsjourney.Models.Entity.userEntity;
import com.example.wordsjourney.Repos.signupRepoINT;
import lombok.AllArgsConstructor;
import com.example.wordsjourney.Config.Mapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class signupService implements signupINT {
    signupRepoINT userRepoINT;

    @Override
    public boolean saveUser(userDTO user) {
        Mapper mapper = new Mapper();
        try{
            userRepoINT.save(mapper.getmap().map(user, userEntity.class));
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }
    @Override
    public boolean existingUserByEmailAndPassword(String email , String password) {
       return userRepoINT.existsByEmailAndPassword(email,password);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepoINT.existsByEmail(email);
    }

    public boolean updateUser(String email, String name, String picture) {
        try {
            // Fetch the existing user by email
            userEntity existingUser = userRepoINT.findByEmail(email);

            if (existingUser != null) {
                // Update only the fields that are supposed to change
                existingUser.setName(name);
                existingUser.setPicture(picture);

                // Save the updated user entity
                userRepoINT.save(existingUser);
                return true;
            }
        } catch (Exception e) {
            // Handle exception (optional logging)
            return false;
        }
        return false; // User not found or save failed
    }

    @Override
    public userDTO getUserData(String email) {
        Mapper mapper = new Mapper();
        return mapper.getmap().map(userRepoINT.getByEmail(email),userDTO.class);
    }

}
