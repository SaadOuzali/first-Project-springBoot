package com.serverside.demoThymeleaf.service;


import com.serverside.demoThymeleaf.model.dto.PatientReqDTO;
import com.serverside.demoThymeleaf.model.dto.UpdateReqDTO;
import com.serverside.demoThymeleaf.model.entitie.Patient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface PatientService {

    Patient createPatient(Patient patient);
    public Page<Patient> findAllPatients(int page);
    public Patient updatePatient(UpdateReqDTO updateReqDTO);
}
