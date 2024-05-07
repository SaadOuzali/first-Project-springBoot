package com.serverside.demoThymeleaf.model.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientReqDTO {

    private String patientName;

    private boolean ismalade;
}
