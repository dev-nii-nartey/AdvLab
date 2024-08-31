package com.advanced_lab.controllers;

import com.advanced_lab.iservices.HospitalizationService;
import com.advanced_lab.models.Hospitalization;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitalizations")
public class HospitalizationController {

    private final HospitalizationService hospitalizationService;

    @Autowired
    public HospitalizationController(HospitalizationService hospitalizationService) {
        this.hospitalizationService = hospitalizationService;
    }

    @PostMapping
    public ResponseEntity<Hospitalization> createHospitalization(@RequestBody Hospitalization hospitalization) {
        Hospitalization createdHospitalization = hospitalizationService.createHospitalization(hospitalization);
        return new ResponseEntity<>(createdHospitalization, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospitalization> getHospitalizationById(@PathVariable String id) {
        Hospitalization hospitalization = hospitalizationService.getHospitalizationById(id);
        return ResponseEntity.ok(hospitalization);
    }

    @GetMapping
    public ResponseEntity<List<Hospitalization>> getAllHospitalizations() {
        List<Hospitalization> hospitalizations = hospitalizationService.getAllHospitalizations();
        return ResponseEntity.ok(hospitalizations);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Hospitalization>> getHospitalizationsByPatient(@PathVariable String patientId) {
        List<Hospitalization> hospitalizations = hospitalizationService.getHospitalizationsByPatient(patientId);
        return ResponseEntity.ok(hospitalizations);
    }

    @GetMapping("/ward/{wardId}")
    public ResponseEntity<List<Hospitalization>> getHospitalizationsByWard(@PathVariable String wardId) {
        List<Hospitalization> hospitalizations = hospitalizationService.getHospitalizationsByWard(wardId);
        return ResponseEntity.ok(hospitalizations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hospitalization> updateHospitalization(@PathVariable String id, @RequestBody Hospitalization hospitalization) {
        Hospitalization updatedHospitalization = hospitalizationService.updateHospitalization(id, hospitalization);
        return ResponseEntity.ok(updatedHospitalization);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospitalization(@PathVariable String id) {
        hospitalizationService.deleteHospitalization(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{hospitalizationId}/patient/{patientId}")
    public ResponseEntity<Hospitalization> linkPatientToHospitalization(@PathVariable String hospitalizationId, @PathVariable String patientId) {
        try {
            Hospitalization updatedHospitalization = hospitalizationService.linkPatientToHospitalization(hospitalizationId, patientId);
            return ResponseEntity.ok(updatedHospitalization);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

