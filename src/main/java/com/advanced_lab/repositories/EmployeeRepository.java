//package com.advanced_lab.repositories;
//
//import com.advanced_lab.models.Doctor;
//import com.advanced_lab.models.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//
//@Repository
//public interface EmployeeRepository extends MongoRepository<Employee, String> {
//    @Modifying
//    @Transactional
//    int softDeleteEmployeeById(@Param("id") Long id);
//
//    List<Employee> findAllNonDeletedEmployees();
//
//    default boolean softDeleteEmployee(Long id) {
//        return softDeleteEmployeeById(id) > 0;
//    }
//
//
//    List<Employee> findAllByIsDeleted(Boolean isDeleted);
//}
