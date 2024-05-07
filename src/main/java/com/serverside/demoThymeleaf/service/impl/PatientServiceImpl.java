package com.serverside.demoThymeleaf.service.impl;

import com.serverside.demoThymeleaf.error.PatientError;
import com.serverside.demoThymeleaf.model.dto.PatientReqDTO;
import com.serverside.demoThymeleaf.model.dto.UpdateReqDTO;
import com.serverside.demoThymeleaf.model.entitie.Patient;
import com.serverside.demoThymeleaf.model.mapper.PatientMapper;
import com.serverside.demoThymeleaf.repository.PatientRepo;
import com.serverside.demoThymeleaf.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PatientServiceImpl implements PatientService {

   private PatientRepo patientRepo;
    private PatientMapper patientMapper;

   PatientServiceImpl(PatientRepo patientRepo,PatientMapper patientMapper){

       this.patientRepo=patientRepo;
       this.patientMapper=patientMapper;
   }
    @Override
    public Patient createPatient(Patient patient) {
       return this.patientRepo.save(patient) ;
    }

    public Page<Patient> findAllPatients(int page) {
       return this.patientRepo.findAll(PageRequest.of(page,5));

    }


//    public Patient updatePatient(UpdateReqDTO updateReqDTO) {
//       Patient patient=this.patientMapper.toPatientEntity(updateReqDTO);
//       return this.patientRepo.save(patient);
//    }

    public Patient updatePatient(UpdateReqDTO updateReqDTO) {
        Patient patient=this.patientRepo.findById(updateReqDTO.getPatientId()).get();
        patient.setPatientName(updateReqDTO.getPatientName());
        patient.setIsmalade(updateReqDTO.isIsmalade());
        return this.patientRepo.save(patient);
    }

    public Patient deletePatient(Long id) {
        Patient patient= this.patientRepo.findById(id).get();
        patient.setDeletedAt(LocalDateTime.now());
        return this.patientRepo.save(patient);
    }

    public Patient findbyIdPatient(Long id) {
        Optional<Patient> patient = this.patientRepo.findByPatientIdAndDeletedAtIsNull(id);
        if (patient.isPresent()) {
            return patient.get();
        }
        throw new PatientError("user not found");

    }


}
