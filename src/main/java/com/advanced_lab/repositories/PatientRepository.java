package com.advanced_lab.repositories;

import com.advanced_lab.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
