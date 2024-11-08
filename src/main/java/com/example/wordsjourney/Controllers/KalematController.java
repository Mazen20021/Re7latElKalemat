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
    private final signupINT kalemat;

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
            boolean message = kalemat.saveUser(user);
            if (!message) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Couldn't Signup User Exists", "405");
                return ResponseEntity.status(405).body(response);
            } else {
                GeneralResponse<userDTO> response2 = PreperResponse.preperResponse(null, "User Added!", "200");
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
        boolean message = kalemat.existingUserByEmailAndPassword(email,password);
        if (!message) {
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User not Found", "405");
            return ResponseEntity.status(405).body(response);
        } else {
            GeneralResponse<userDTO> response2 = PreperResponse.preperResponse(null, "User Found", "200");
            return ResponseEntity.ok(response2);
        }
        }
    }
    @PutMapping("/update")
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
            boolean existingUser = kalemat.existsByEmail(email);
            if (!existingUser) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User not found", "404");
                return ResponseEntity.status(404).body(response);
            }
            userDTO user = kalemat.getUserData(email);
            if(name.isEmpty() || name.equals(user.getName()))
            {
                boolean isUpdated = kalemat.updateUser(email, user.getName(), picture);
                if (!isUpdated) {
                    GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Failed to update user", "500");
                    return ResponseEntity.status(500).body(response);
                }
            }else if(picture.isEmpty() || picture.equals(user.getPicture()))
            {
                boolean isUpdated = kalemat.updateUser(email, name, user.getPicture());
                if (!isUpdated) {
                    GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Failed to update user", "500");
                    return ResponseEntity.status(500).body(response);
                }

            }else {
                boolean isUpdated = kalemat.updateUser(email, name, picture);
                if (!isUpdated) {
                    GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Failed to update user", "500");
                    return ResponseEntity.status(500).body(response);
                }
            }
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User Updated Successfully!", "200");
            return ResponseEntity.ok(response);
        }
    }
    @GetMapping("/getdata")
    public ResponseEntity<GeneralResponse<userDTO>> Update(
            @RequestHeader(value = "email") String email,
            @RequestHeader(value = "token") String token
    ) {
        if (!token.equals(APIToken.token)) {
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Access Denied", "300");
            return ResponseEntity.status(300).body(response);
        } else {
            boolean existingUser = kalemat.existsByEmail(email);
            if (!existingUser) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User not found", "404");
                return ResponseEntity.status(404).body(response);
            }
            userDTO user = kalemat.getUserData(email);
            if (user == null) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Failed to get user's data", "505");
                return ResponseEntity.status(505).body(response);
            }
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(user, "User Data", "200");
            return ResponseEntity.ok(response);
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<GeneralResponse<userDTO>> Delete(
            @RequestHeader(value = "email") String email,
            @RequestHeader(value = "token") String token
    ) {
        if (!token.equals(APIToken.token)) {
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Access Denied", "300");
            return ResponseEntity.status(300).body(response);
        } else {
            boolean existingUser = kalemat.existsByEmail(email);
            if (!existingUser) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User not found", "404");
                return ResponseEntity.status(404).body(response);
            }
            boolean deleted = kalemat.deleteUserData(email);
            if (!deleted) {
                GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "Failed to delete this user", "510");
                return ResponseEntity.status(510).body(response);
            }
            GeneralResponse<userDTO> response = PreperResponse.preperResponse(null, "User Deleted!", "200");
            return ResponseEntity.ok(response);
        }
    }

}
