package com.advanced_lab.repositories;

import com.advanced_lab.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.isDeleted = true WHERE e.id = :id")
    int softDeleteEmployeeById(@Param("id") Long id);

    @Query("SELECT e FROM Employee e WHERE e.isDeleted = false")
    List<Employee> findAllNonDeletedEmployees();

    default boolean softDeleteEmployee(Long id) {
        return softDeleteEmployeeById(id) > 0;
    }


    List<Employee> findAllByIsDeleted(Boolean isDeleted);
}
