package com.advanced_lab.iservices;

import com.advanced_lab.dto.DepartmentDTO;
import java.util.List;

public interface DepartmentService {
    DepartmentDTO createDepartment(DepartmentDTO department);
    DepartmentDTO getDepartmentById(String id);
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO setDirector(String departmentId, String doctorId);
    DepartmentDTO updateDepartment(String id, DepartmentDTO department);
    void deleteDepartment(String id);
}
