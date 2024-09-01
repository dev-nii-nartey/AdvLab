package com.advanced_lab.services;

import com.advanced_lab.exceptions.DoctorAlreadyExistsException;
import com.advanced_lab.exceptions.DoctorDoesNotExistException;
import com.advanced_lab.iservices.DoctorService;
import com.advanced_lab.models.Doctor;
import com.advanced_lab.repositories.DoctorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Doctor createDoctor(Doctor doctor) throws DoctorAlreadyExistsException {
        Optional<Doctor> existingDoctor = doctorRepository.findBySurnameAndSpecialtyAndTelephoneNumberAndIsDeletedFalse(
                doctor.getSurname(), doctor.getSpecialty(), doctor.getTelephoneNumber());

        if (existingDoctor.isPresent()) {
            throw new DoctorAlreadyExistsException("A doctor with the same surname, specialty, and telephone number already exists.");
        }

        doctor.setEmployeeType("Doctor");
        return doctorRepository.save(doctor);
    }

    @Override
    @Cacheable(value = "doctorCache", key = "#doctorId")
    public Doctor getDoctorById(String doctorId) {
        return doctorRepository.findByIdAndEmployeeTypeAndIsDeletedFalse(doctorId, "Doctor")
                .orElseThrow(() -> new DoctorDoesNotExistException("Doctor not found with Id: " + doctorId));
    }

    @Override
    @Cacheable(value = "allDoctorsCache", unless = "#result.isEmpty()")
    public List<Doctor> getAllDoctors() {
        logger.info("Getting all doctors");
        return doctorRepository.findByEmployeeTypeAndIsDeletedFalse("Doctor");
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "doctorCache", key = "#doctorId"),
            @CacheEvict(value = "allDoctorsCache", allEntries = true)
    })
    public Doctor updateDoctor(String doctorId, Doctor doctor) {
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
    public void deleteDoctor(String id) {
        Doctor doctor = getDoctorById(id);
        doctor.setDeleted(true);
        doctorRepository.save(doctor);
    }
}
