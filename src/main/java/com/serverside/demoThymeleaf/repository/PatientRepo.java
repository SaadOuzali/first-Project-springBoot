package com.serverside.demoThymeleaf.repository;

import com.serverside.demoThymeleaf.model.entitie.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepo extends JpaRepository<Patient,Long> {

    Optional<Patient> findByPatientIdAndDeletedAtIsNull(Long id);
}
