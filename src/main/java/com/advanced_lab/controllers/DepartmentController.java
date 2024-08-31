package com.advanced_lab.controllers;

import com.advanced_lab.dto.DepartmentDTO;
import com.advanced_lab.iservices.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentDTO department) {
        DepartmentDTO createdDepartment = departmentService.createDepartment(department);
        return new ResponseEntity<>(createdDepartment.getName() +"-Department has been created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable String id) {
        DepartmentDTO department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable String id, @RequestBody DepartmentDTO department) {
        DepartmentDTO updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable String id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{departmentId}/director/{doctorId}")
    public ResponseEntity<DepartmentDTO> setDepartmentDirector(@PathVariable String departmentId, @PathVariable String doctorId) {
        DepartmentDTO updatedDepartment = departmentService.setDirector(departmentId, doctorId);
        return ResponseEntity.ok(updatedDepartment);
    }
}
