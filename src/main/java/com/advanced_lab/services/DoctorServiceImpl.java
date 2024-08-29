package com.advanced_lab.services;

import com.advanced_lab.iservices.DoctorService;
import com.advanced_lab.models.Doctor;
import com.advanced_lab.models.Employee;
import com.advanced_lab.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        Employee employee = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        if (!(employee instanceof Doctor)) {
            throw new RuntimeException("Employee with id: " + id + " is not a Doctor");
        }

        Doctor doctor = (Doctor) employee;

        if (doctor.isDeleted()) {
            throw new RuntimeException("Doctor with id: " + id + " has been deleted");
        }

        return doctor;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAllNonDeletedDoctors();
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = getDoctorById(id);
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
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
