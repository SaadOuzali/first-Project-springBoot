package com.serverside.demoThymeleaf.model;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;

import java.util.stream.Collectors;

@ControllerAdvice
//@Slf4j
public class  ExceptionHandlerController {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<HashMap<String, Object>> handleexception(BindException exception){
        List<String> errorsMessage=exception.getAllErrors().stream().map(err->err.getDefaultMessage()).collect(Collectors.toList());
        HashMap<String,Object> errs=new HashMap<>();

//        Logger log= LoggerFactory.getLogger(ExceptionHandlerController.class);
//        log.info("everything is good ");

        //Methode 2 to get the filed name and value
//        HashMap<String,String> errors=new HashMap<>();
//        exception.getBindingResult().getFieldErrors().forEach(err->{
//            errors.put(err.getField(),err.getDefaultMessage());
//        });
        errs.put("status", 404);
        errs.put("errors",errorsMessage);
        return new ResponseEntity<HashMap<String, Object>>(errs, HttpStatus.BAD_REQUEST);
    }
}
