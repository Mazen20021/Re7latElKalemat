package com.example.wordsjourney.Controllers;

import com.example.wordsjourney.Models.Dtos.APIToken;
import com.example.wordsjourney.Models.Dtos.userDTO;
import com.example.wordsjourney.Models.Entity.userEntity;
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
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Couldn't Signup User Exists", "405");
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
    @PostMapping("/update")
    public ResponseEntity<GeneralResponse<userDTO>> Update(
            @RequestHeader(value = "email") String email,
            @RequestHeader(value = "name") String name,
            @RequestHeader(value = "picture") String picture,
            @RequestHeader(value = "token") String token
    ) {
        if (!token.equals(APIToken.token)) {
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Access Denied", "300");
            return ResponseEntity.status(300).body(response);
        } else {
            // Check if user exists
            boolean existingUser = signUp.existsByEmail(email);
            if (!existingUser) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User not found", "404");
                return ResponseEntity.status(404).body(response);
            }

            // Call the updated method with the necessary parameters
            boolean isUpdated = signUp.updateUser(email, name, picture);
            if (!isUpdated) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Failed to update user", "500");
                return ResponseEntity.status(500).body(response);
            }

            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User Updated Successfully!", "200");
            return ResponseEntity.ok(response);
        }
    }


}
