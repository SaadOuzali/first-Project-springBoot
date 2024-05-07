package com.serverside.demoThymeleaf.error;

public class PatientError extends RuntimeException{
   public PatientError(String msg){
        super(msg);
    }
}
