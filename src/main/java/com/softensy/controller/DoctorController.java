package com.softensy.controller;

import com.softensy.dao.DoctorDAO;
import com.softensy.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorDAO doctorDAO;



    @GetMapping(value = "/doctors")
    public List<Doctor> getAllDoctors (){
        List<Doctor> doctors= doctorDAO.getAllDoctors();
        return doctors;
    }

    @GetMapping(value = "{id}")
    public Doctor getDoctor (@PathVariable("id") Long doctorId){
        return doctorDAO.getDoctorById(doctorId);
    }


    @PostMapping()
    public void saveDoctor (@RequestBody Doctor doctor){
        doctorDAO.save(doctor);
    }


    @DeleteMapping(value = "{id}")
    public void deleteDoctor (@PathVariable Long id){
        doctorDAO.delete(id);
    }

    @PutMapping(value = "{id}")
    public void updateDoctor (@PathVariable Long id, @RequestBody Doctor doctor){
        doctorDAO.update(id,doctor);
    }

}
