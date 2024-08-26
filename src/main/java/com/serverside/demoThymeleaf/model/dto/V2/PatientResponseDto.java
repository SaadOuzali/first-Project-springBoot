package com.serverside.demoThymeleaf.model.dto.V2;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientResponseDto {

    private String patientName;

    private boolean ismalade;
}
