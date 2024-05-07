package com.serverside.demoThymeleaf.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(PatientError.class)
    public ResponseEntity<HashMap<String,Object>> globalErrorHandler(PatientError err){
        HashMap<String,Object> error=new HashMap<>();



        error.put("message",err.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

//
    }
}
