package com.advanced_lab.repositories;

import com.advanced_lab.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends EmployeeRepository {
    @Query("SELECT d FROM Doctor d WHERE d.isDeleted = false")
    List<Doctor> findAllNonDeletedDoctors();
}
