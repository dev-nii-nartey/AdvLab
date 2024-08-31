package com.advanced_lab.iservices;

import com.advanced_lab.models.Ward;
import java.util.List;

public interface WardService {
    Ward createWard(Ward ward);
    Ward getWardById(Long id);
    List<Ward> getAllWards();
    Ward updateWard(Long id, Ward ward);
    void deleteWard(Long id);
    List<Ward> getWardsByDepartment(Long departmentId);
    Ward setSupervisor(Long wardId, Long nurseId);
    Ward assignNurseToWard(Long wardId, Long nurseId);

    Ward assignDoctorToWard(Long wardId, Long doctorId);
}
