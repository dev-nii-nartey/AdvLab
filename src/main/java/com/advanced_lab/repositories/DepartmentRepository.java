package com.advanced_lab.repositories;

import com.advanced_lab.models.Department;
import com.advanced_lab.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
