package com.example.wordsjourney.ExceptionHandler;

import com.example.wordsjourney.Models.Dtos.userDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomExceptions extends RuntimeException{

    private String code;
    private String message;
    private userDTO UserDTO;
}
