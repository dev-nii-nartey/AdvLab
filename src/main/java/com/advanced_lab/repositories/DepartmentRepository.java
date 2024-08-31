package com.advanced_lab.repositories;

import com.advanced_lab.models.Department;
import com.advanced_lab.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {
}
