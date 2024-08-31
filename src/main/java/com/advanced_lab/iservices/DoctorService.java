package com.advanced_lab.iservices;

import com.advanced_lab.models.Doctor;
import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Doctor doctor);
    Doctor getDoctorById(String id);
    List<Doctor> getAllDoctors();
    Doctor updateDoctor(String id, Doctor doctor);
    void deleteDoctor(String id);
}
