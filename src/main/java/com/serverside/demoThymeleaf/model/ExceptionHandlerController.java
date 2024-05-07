package com.serverside.demoThymeleaf.model;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class  ExceptionHandlerController {
    @ExceptionHandler(BindException.class)
    public ResponseEntity<HashMap<String, Object>> handleexception(BindException exception){
        List<String> errorsMessage=exception.getAllErrors().stream().map(err->err.getDefaultMessage()).collect(Collectors.toList());
        HashMap<String,Object> errs=new HashMap<>();
        errs.put("status", 404);
        errs.put("errors",errorsMessage);
        return new ResponseEntity<>(errs, HttpStatus.BAD_REQUEST);
    }
}
