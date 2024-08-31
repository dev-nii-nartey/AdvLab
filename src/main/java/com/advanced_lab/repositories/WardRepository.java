package com.advanced_lab.repositories;

import com.advanced_lab.models.Doctor;
import com.advanced_lab.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends MongoRepository<Ward, String> {
    List<Ward> findByDepartmentId(String departmentId);
}
