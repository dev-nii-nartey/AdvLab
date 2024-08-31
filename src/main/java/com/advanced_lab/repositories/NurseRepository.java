package com.advanced_lab.repositories;

import com.advanced_lab.models.Nurse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NurseRepository extends MongoRepository<Nurse, String> {

    Optional<Nurse> findByIdAndEmployeeTypeAndIsDeletedFalse(String id, String employeeType);

    List<Nurse> findByEmployeeTypeAndIsDeletedFalse(String employeeType);
}
