package com.advanced_lab.repositories;

import com.advanced_lab.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    List<Ward> findByDepartmentId(Long departmentId);
}
