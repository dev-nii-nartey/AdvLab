package com.advanced_lab.controllers;


import com.advanced_lab.models.Ward;
import com.advanced_lab.services.WardServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wards")
@AllArgsConstructor
public class WardController {

private final WardServiceImpl wardService;


    @PostMapping
    public ResponseEntity<Ward> createWard(@RequestBody Ward ward) {
        Ward createdWard = wardService.createWard(ward);
        return new ResponseEntity<>(createdWard, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable String id) {
        Ward ward = wardService.getWardById(id);
        return ResponseEntity.ok(ward);
    }

    @GetMapping
    public ResponseEntity<List<Ward>> getAllWards() {
        List<Ward> wards = wardService.getAllWards();
        return ResponseEntity.ok(wards);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Ward>> getWardsByDepartment(@PathVariable String departmentId) {
        List<Ward> wards = wardService.getWardsByDepartment(departmentId);
        return ResponseEntity.ok(wards);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ward> updateWard(@PathVariable String id, @RequestBody Ward ward) {
        Ward updatedWard = wardService.updateWard(id, ward);
        return ResponseEntity.ok(updatedWard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWard(@PathVariable String id) {
        wardService.deleteWard(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{wardId}/supervisor/{nurseId}")
    public ResponseEntity<Ward> setWardSupervisor(@PathVariable String wardId, @PathVariable String nurseId) {
        Ward updatedWard = wardService.setSupervisor(wardId, nurseId);
        return ResponseEntity.ok(updatedWard);
    }

    @PutMapping("/{wardId}/nurses/{nurseId}")
    public ResponseEntity<Ward> assignNurseToWard(@PathVariable String wardId, @PathVariable String nurseId) {
        try {
            Ward updatedWard = wardService.assignNurseToWard(wardId, nurseId);
            return ResponseEntity.ok(updatedWard);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{wardId}/doctors/{doctorId}")
    public ResponseEntity<Ward> assignDoctorToWard(@PathVariable String wardId, @PathVariable String doctorId) {
        try {
            Ward updatedWard = wardService.assignDoctorToWard(wardId, doctorId);
            return ResponseEntity.ok(updatedWard);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
