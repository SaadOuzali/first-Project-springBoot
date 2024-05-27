package com.serverside.demoThymeleaf.controller;


import com.serverside.demoThymeleaf.model.dto.PatientReqDTO;
import com.serverside.demoThymeleaf.model.dto.UpdateReqDTO;
import com.serverside.demoThymeleaf.model.dto.V2.PatientReqDTOV2;
import com.serverside.demoThymeleaf.model.entitie.Patient;
import com.serverside.demoThymeleaf.model.mapper.PatientMapper;
import com.serverside.demoThymeleaf.service.impl.PatientServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/patient")
@Profile("dev")
public class PatientController {

    private PatientServiceImpl patientService;
    private PatientMapper patientMapper;

    @Value("${users.info}")
    private String userInfo;

    PatientController(PatientServiceImpl patientServiceImpl,PatientMapper patientMapper) {
        this.patientService = patientServiceImpl;
        this.patientMapper=patientMapper;
    }

    @PostMapping("/create")
    @ResponseBody
    public Patient createpatient(@RequestBody PatientReqDTO patientreq) {
        System.out.println("patient namùe        "+patientreq.isIsmalade());
        Patient patient=this.patientService.createPatient(patientMapper.toPatientEntity(patientreq));
        return patient;
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public Patient findPatient(@PathVariable Long id){
        return this.patientService.findbyIdPatient(id);
    }

    @PutMapping("/update")
    @ResponseBody
    public Patient updatePatient(@RequestBody UpdateReqDTO updateReqDTO){
      return this.patientService.updatePatient(updateReqDTO);
    }


    @DeleteMapping("/delete/{id}")
    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    public Patient deletePatient(@PathVariable Long id){
        return this.patientService.deletePatient(id);
    }

    @GetMapping("/homme")
    public String get(Model model,@RequestParam(name = "page", defaultValue="0") int page){
        Page<Patient> patients=this.patientService.findAllPatients(page);
        System.out.println("total pages    "+patients.getTotalPages());
        model.addAttribute("patients",patients.getContent());
        model.addAttribute("pages",new int[patients.getTotalPages()]);
        return "Home";
    }

    @GetMapping("/delete")
    @ResponseBody
//    @PreAuthorize("hasRole('ADMIN')")
    public String redirect(@RequestParam("id") Long id){
        return "redirect:/patient/homme";
    }

    @GetMapping(value = "/delete/{id}",params = "v=1.1.0")
    @ResponseBody
    public String redirec(@PathVariable("id") Long id){
        return "redirect:/patient/homme";
    }


    @GetMapping(value = "/env")
    @ResponseBody
    public String env(){
        if (userInfo.equals("developement")){
            return "developement";
        } else if (userInfo.equals("production")) {
            return "production";
        }
        return "redirect:/patient/homme";
    }

    @GetMapping("/auth")
    @ResponseBody
//
    public String auth(){
        return "dazttttttt";
    }


    //test validation constraint
    @PostMapping(value = "/test",params = "v=1.1.0")
    @ResponseBody
    public Patient test(@RequestBody @Valid PatientReqDTOV2 patientReqDTOV2){
        Patient patient=this.patientService.createPatient(patientMapper.toPatientEntity2(patientReqDTOV2));
        return patient;
    }




}
