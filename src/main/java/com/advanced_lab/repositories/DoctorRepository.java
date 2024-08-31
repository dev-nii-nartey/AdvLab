package com.advanced_lab.repositories;

import com.advanced_lab.models.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {

    Optional<Doctor> findByIdAndEmployeeTypeAndIsDeletedFalse(String id, String employeeType);

    List<Doctor> findByEmployeeTypeAndIsDeletedFalse(String employeeType);
}
