package com.serverside.demoThymeleaf.model.dto.V2;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientReqDTOV2 {

    @NotNull(message = "da5l patientName la7mar ta3 bok")
    private String patientName;

    @Email(message = "message not be nllll")
    @NotNull
    private String email;

    private boolean ismalade;
}

