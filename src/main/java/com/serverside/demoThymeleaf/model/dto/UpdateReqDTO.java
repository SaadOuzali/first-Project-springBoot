package com.serverside.demoThymeleaf.model.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateReqDTO {

    private Long patientId;
        private String patientName;

        private boolean ismalade;

}
