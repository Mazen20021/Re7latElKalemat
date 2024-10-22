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
}
