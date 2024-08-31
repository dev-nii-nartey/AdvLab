package com.advanced_lab.services;

import com.advanced_lab.iservices.WardService;
import com.advanced_lab.models.Doctor;
import com.advanced_lab.models.Nurse;
import com.advanced_lab.models.Ward;
import com.advanced_lab.repositories.DoctorRepository;
import com.advanced_lab.repositories.NurseRepository;
import com.advanced_lab.repositories.WardRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;
    private final NurseRepository nurseRepository;
    private final DoctorRepository doctorRepository;


    public WardServiceImpl(WardRepository wardRepository, NurseRepository nurseRepository,DoctorRepository doctorRepository) {
        this.wardRepository = wardRepository;
        this.nurseRepository = nurseRepository;
        this.doctorRepository = doctorRepository;

    }

    @Override
    public Ward createWard(Ward ward) {
        return wardRepository.save(ward);
    }

    @Override
    public Ward getWardById(String id) {
        return wardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ward not found with id: " + id));
    }

    @Override
    public List<Ward> getAllWards() {
        return wardRepository.findAll();
    }

    @Override
    public Ward updateWard(String id, Ward ward) {
        Ward existingWard = getWardById(id);
        existingWard.setNumber(ward.getNumber());
        existingWard.setNumberOfBeds(ward.getNumberOfBeds());
        existingWard.setDepartment(ward.getDepartment());
        existingWard.setSupervisor(ward.getSupervisor());
        return wardRepository.save(existingWard);
    }

    @Override
    public void deleteWard(String id) {
        wardRepository.deleteById(id);
    }

    @Override
    public List<Ward> getWardsByDepartment(String departmentId) {
        return wardRepository.findByDepartmentId(departmentId);
    }

    @Override
    @Transactional
    public Ward setSupervisor(String wardId, String nurseId) {
        Ward ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new EntityNotFoundException("Ward not found"));
        Nurse nurse = nurseRepository.findById(nurseId)
                .orElseThrow(() -> new EntityNotFoundException("Nurse not found"));

        ward.setSupervisor(nurse);
        return wardRepository.save(ward);
    }

    @Override
    public Ward assignNurseToWard(String wardId, String nurseId) {
        return null;
    }

    @Override
    public Ward assignDoctorToWard(String wardId, String doctorId) {
        return null;
    }

//    @Override
//    @Transactional
//    public Ward assignNurseToWard(String wardId, String nurseId) {
//        Ward ward = wardRepository.findById(wardId)
//                .orElseThrow(() -> new EntityNotFoundException("Ward not found with id: " + wardId));
//        Nurse nurse = nurseRepository.findById(nurseId)
//                .orElseThrow(() -> new EntityNotFoundException("Nurse not found with id: " + nurseId));
//
//        ward.getNurses().add(nurse);
//        return wardRepository.save(ward);
//    }
//
//
//    @Override
//    @Transactional
//    public Ward assignDoctorToWard(String wardId, String doctorId) {
//        Ward ward = wardRepository.findById(wardId)
//                .orElseThrow(() -> new EntityNotFoundException("Ward not found with id: " + wardId));
//        Doctor doctor = doctorRepository.findById(doctorId)
//                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + doctorId));
//
//        ward.getDoctors().add(doctor);
//        return wardRepository.save(ward);
//    }

}
