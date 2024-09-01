package com.advanced_lab.controllers;


import com.advanced_lab.models.Doctor;
import com.advanced_lab.services.DoctorServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@Slf4j
public class DoctorController {

    private final DoctorServiceImpl doctorService;

    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor createdDoctor = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable String id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable String id, @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
