package com.advanced_lab.iservices;

import com.advanced_lab.dto.DepartmentDTO;
import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO department);
    DepartmentDTO getDepartmentById(Long id);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO setDirector(Long departmentId, Long doctorId);
    DepartmentDTO updateDepartment(Long id, DepartmentDTO department);
    void deleteDepartment(Long id);
}
