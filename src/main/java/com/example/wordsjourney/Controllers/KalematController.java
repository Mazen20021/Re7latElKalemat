package com.example.wordsjourney.Controllers;

import com.example.wordsjourney.Models.Dtos.APIToken;
import com.example.wordsjourney.Models.Dtos.userDTO;
import com.example.wordsjourney.Models.GeneralResponse;
import com.example.wordsjourney.Services.signupINT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.example.wordsjourney.util.PreperResponse;

@RestController
@RequiredArgsConstructor
public class KalematController {
    private final signupINT signUp;

    @PostMapping("/signup")
    public ResponseEntity<GeneralResponse<userDTO>> SignUp(@RequestHeader(value = "name") String name,@RequestHeader(value = "email") String email, @RequestHeader(value = "password") String password,@RequestHeader(value = "picture") String picture ,@RequestHeader(value = "token") String token ) {
        if(!token.equals(APIToken.token))
        {
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Access Denied", "300");
            return ResponseEntity.status(300).body(response);
        }else {
            userDTO user = new userDTO();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPicture(picture);
            user.setScore(0L);
            boolean message = signUp.saveUser(user);
            if (!message) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Couldn't Signup", "405");
                return ResponseEntity.status(405).body(response);
            } else {
                GeneralResponse<userDTO> response2 = PreperResponse.preperResponse(null, "User Added !", "200");
                return ResponseEntity.ok(response2);
            }
        }

    }
    @GetMapping("/login")
    public ResponseEntity<GeneralResponse<userDTO>> Login(@RequestHeader(value = "email") String email, @RequestHeader(value = "password") String password,@RequestHeader(value = "token") String token ) {
        if(!token.equals(APIToken.token))
        {
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Access Denied", "300");
            return ResponseEntity.status(300).body(response);
        }else {
        boolean message = signUp.existingUserByEmailAndPassword(email,password);
        if (!message) {
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User not Found", "405");
            return ResponseEntity.status(405).body(response);
        } else {
            GeneralResponse<userDTO> response2 = PreperResponse.preperResponse(null, "User Found", "200");
            return ResponseEntity.ok(response2);
        }
        }
    }
}
