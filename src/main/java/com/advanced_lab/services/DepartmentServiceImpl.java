package com.advanced_lab.services;

import com.advanced_lab.dto.DepartmentDTO;
import com.advanced_lab.iservices.DepartmentService;
import com.advanced_lab.models.Department;
import com.advanced_lab.models.Doctor;
import com.advanced_lab.models.Employee;
import com.advanced_lab.repositories.DepartmentRepository;
import com.advanced_lab.repositories.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;


    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DoctorRepository doctorRepository) {
        this.departmentRepository = departmentRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentDTO department) {
        Department newDepartment = Department.builder().name(department.getName()).code(department.getCode()).building(department.getBuilding()).build();
        Department createdDepartment = departmentRepository.save(newDepartment);
        return DepartmentDTO.builder().name(createdDepartment.getName()).build();
    };


    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        Department foundDepartment = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found" + id));
        return DepartmentDTO.builder().id(foundDepartment.getId()).code(foundDepartment.getCode()).name(foundDepartment.getName()).building(foundDepartment.getBuilding()).build();
    }


    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(department -> DepartmentDTO.builder()
                        .id(department.getId())
                        .code(department.getCode())
                        .name(department.getName())
                        .building(department.getBuilding())
                        // Add other fields as necessary
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO department) {
        Department existingDepartment  = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found" + id));
        existingDepartment.setCode(department.getCode());
        existingDepartment.setName(department.getName());
        existingDepartment.setBuilding(department.getBuilding());
       Department updateDepartment = departmentRepository.save(existingDepartment);
        return DepartmentDTO.builder().code(updateDepartment.getCode()).name(updateDepartment.getName()).build();
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }


    @Override
    @Transactional
    public DepartmentDTO setDirector(Long departmentId, Long doctorId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + departmentId));

        Employee employee = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));

        if (!(employee instanceof Doctor)) {
            throw new IllegalArgumentException("Employee with id " + doctorId + " is not a Doctor");
        }

        Doctor doctor = (Doctor) employee;
        department.setDirector(doctor);
        departmentRepository.save(department);

        return DepartmentDTO.builder()
                .id(department.getId())
                .code(department.getCode())
                .name(department.getName())
//                .director(doctor.)
                .build();
    }
}
