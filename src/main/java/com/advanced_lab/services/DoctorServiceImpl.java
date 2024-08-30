package com.advanced_lab.services;

import com.advanced_lab.iservices.DoctorService;
import com.advanced_lab.models.Doctor;
import com.advanced_lab.models.Employee;
import com.advanced_lab.repositories.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private static final Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    @CacheEvict(value = {"doctorCache", "allDoctorsCache"}, allEntries = true)
    public Doctor createDoctor(Doctor doctor) {
        logger.info("Creating new doctor: {}", doctor);
        return doctorRepository.save(doctor);
    }

    @Override
    @Cacheable(value = "doctorCache", key = "#doctorId")
    public Doctor getDoctorById(Long doctorId) {
        logger.info("Fetching doctor data for id: {}", doctorId);
        Employee employee = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        if (!(employee instanceof Doctor)) {
            throw new RuntimeException("Employee with id: " + doctorId + " is not a Doctor");
        }

        Doctor doctor = (Doctor) employee;

        if (doctor.isDeleted()) {
            throw new RuntimeException("Doctor with id: " + doctorId + " has been deleted");
        }

        return doctor;
    }

    @Override
    @Cacheable(value = "allDoctorsCache", unless = "#result.isEmpty()")
    public List<Doctor> getAllDoctors() {
        logger.info("Fetching all non-deleted doctors");
        return doctorRepository.findAllNonDeletedDoctors();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "doctorCache", key = "#doctorId"),
            @CacheEvict(value = "allDoctorsCache", allEntries = true)
    })
    public Doctor updateDoctor(Long doctorId, Doctor doctor) {
        logger.info("Updating doctor with id: {}", doctorId);
        Doctor existingDoctor = getDoctorById(doctorId);
        existingDoctor.setSurname(doctor.getSurname());
        existingDoctor.setFirstName(doctor.getFirstName());
        existingDoctor.setAddress(doctor.getAddress());
        existingDoctor.setTelephoneNumber(doctor.getTelephoneNumber());
        existingDoctor.setSalary(doctor.getSalary());
        existingDoctor.setSpecialty(doctor.getSpecialty());
        existingDoctor.setDepartment(doctor.getDepartment());
        return doctorRepository.save(existingDoctor);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "doctorCache", key = "#id"),
            @CacheEvict(value = "allDoctorsCache", allEntries = true)
    })
    public void deleteDoctor(Long id) {
        logger.info("Deleting doctor with id: {}", id);
        doctorRepository.deleteById(id);
    }
}