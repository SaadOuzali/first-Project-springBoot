package com.serverside.demoThymeleaf.model.mapper;


import com.serverside.demoThymeleaf.model.dto.PatientReqDTO;
import com.serverside.demoThymeleaf.model.dto.UpdateReqDTO;
import com.serverside.demoThymeleaf.model.dto.V2.PatientReqDTOV2;
import com.serverside.demoThymeleaf.model.entitie.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toPatientEntity(PatientReqDTO patientReqDTO);

    Patient toPatientEntity(UpdateReqDTO updateReqDTO);

    Patient toPatientEntity2(PatientReqDTOV2 patientReqDTOV2);

}
