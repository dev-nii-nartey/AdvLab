package com.advanced_lab.repositories;

import com.advanced_lab.models.Doctor;
import com.advanced_lab.models.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalizationRepository extends MongoRepository<Hospitalization, String> {
    List<Hospitalization> findByWardId(String wardId);

    List<Hospitalization> findByPatientPatientNumber(String patientId);
}
