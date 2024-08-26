package com.serverside.demoThymeleaf.service.impl;

import com.serverside.demoThymeleaf.error.PatientError;
import com.serverside.demoThymeleaf.model.dto.V2.PatientResponseDto;
import com.serverside.demoThymeleaf.model.entitie.Patient;
import com.serverside.demoThymeleaf.model.mapper.PatientMapper;
import com.serverside.demoThymeleaf.repository.PatientRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    //declare the dependencies
    @Mock
    private PatientRepo patientRepo;
    @Mock
    private PatientMapper patientMapper;


    @BeforeEach
    public void run(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findbyidandDeletedAtIsNull(){
        Long pateientId=1L;
        Patient patient=Patient.builder()
                .patientId(pateientId)
                .patientName("saad")
                .ismalade(false)
                .build();
        Mockito.when(patientRepo.findByPatientIdAndDeletedAtIsNull(pateientId))
                .thenReturn(Optional.of(patient));

        Patient patient1 = patientService.findbyIdPatient(pateientId);
        assertNotNull(patient1);
        assertEquals(patient1.getPatientName(), "saad");

    }


    @Test
    public void findById(){
        Long pateientId=1L;
        Patient patient=Patient.builder()
                .patientId(pateientId)
                .patientName("saad")
                .ismalade(false)
                .build();


        Mockito.when(patientRepo.findById(pateientId))
                .thenReturn(Optional.of(patient));
        Mockito.when(patientMapper.toPatientResponseDto(any(Patient.class)))
                .thenReturn(PatientResponseDto.builder()
                        .patientName("saad")
                        .ismalade(false)
                        .build());

        PatientResponseDto patient1 = patientService.findById(pateientId);
        assertNotNull(patient1);
        assertEquals(patient1.getPatientName(), "saad");
        assertEquals(patient1.isIsmalade(), false);
    }


    @Test
    void patient_not_found(){
        Mockito.when(this.patientRepo.findById(1L))
                .thenReturn(Optional.empty());
        PatientError exception = assertThrows(PatientError.class, () -> {
            this.patientService.findById(1L);
        });

        assertEquals(exception.getMessage(), "lai3tini sa7a");
    }

}

