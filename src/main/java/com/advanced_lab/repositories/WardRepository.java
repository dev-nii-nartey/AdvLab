package com.advanced_lab.repositories;

import com.advanced_lab.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Long> {
}
