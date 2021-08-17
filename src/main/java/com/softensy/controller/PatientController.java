package com.softensy.controller;

import com.softensy.dao.PatientDAO;
import com.softensy.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientDAO patientDAO;

    @GetMapping(value = "/patients")
    public List<Patient> getAllPatients (){
        List<Patient> patients= patientDAO.getAllPatients();
        return patients;
    }

    @GetMapping(value = "{id}")
    public Patient getPatient (@PathVariable("id") Long patientId){
        return patientDAO.getPatientById(patientId);
    }


    @PostMapping()
    public void savePatient (@RequestBody Patient patient){
        patientDAO.save(patient);
    }


    @DeleteMapping(value = "{id}")
    public void deletePatient (@PathVariable Long id){
        patientDAO.delete(id);
    }

    @PutMapping(value = "{id}")
    public void updatePatient (@PathVariable Long id, @RequestBody Patient patient){
        patientDAO.update(id,patient);
    }
}
