package com.advanced_lab.repositories;

import com.advanced_lab.models.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
}
