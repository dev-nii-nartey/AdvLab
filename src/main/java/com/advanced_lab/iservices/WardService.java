package com.advanced_lab.iservices;

import com.advanced_lab.models.Ward;
import java.util.List;

public interface WardService {
    Ward createWard(Ward ward);
    Ward getWardById(String id);
    List<Ward> getAllWards();
    Ward updateWard(String id, Ward ward);
    void deleteWard(String id);
    List<Ward> getWardsByDepartment(String departmentId);
    Ward setSupervisor(String wardId, String nurseId);
    Ward assignNurseToWard(String wardId, String nurseId);

    Ward assignDoctorToWard(String wardId, String doctorId);
}
