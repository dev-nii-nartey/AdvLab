package com.advanced_lab.repositories;

import com.advanced_lab.models.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalizationRepository extends JpaRepository<Hospitalization, Long> {
    List<Hospitalization> findByWardId(Long wardId);

    List<Hospitalization> findByPatientPatientNumber(Long patientId);
}
