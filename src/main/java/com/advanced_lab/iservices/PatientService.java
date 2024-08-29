package com.advanced_lab.iservices;

import com.advanced_lab.models.Department;
import com.advanced_lab.models.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);
    Patient getPatientById(Long id);
    List<Patient> getAllPatients();
    Patient updatePatient(Long id, Patient department);
    void deletePatient(Long id);
}
