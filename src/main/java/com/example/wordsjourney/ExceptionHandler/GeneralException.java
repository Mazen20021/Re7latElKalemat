package com.example.wordsjourney.ExceptionHandler;

import com.example.wordsjourney.Models.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.wordsjourney.util.PreperResponse;

@ControllerAdvice
public class GeneralException {
    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(CustomExceptions e){
        GeneralResponse generalResponse = PreperResponse.preperResponse(null,"Fail","404");
        return new ResponseEntity<>(generalResponse, HttpStatus.NOT_FOUND);
    }
}
