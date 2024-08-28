package com.advanced_lab.repositories;

import com.advanced_lab.models.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalizationRepository extends JpaRepository<Hospitalization, Long> {
}
