package com.advanced_lab.iservices;

import com.advanced_lab.models.Nurse;
import java.util.List;

public interface NurseService {
    Nurse createNurse(Nurse nurse);
    Nurse getNurseById(Long id);
    List<Nurse> getAllNurses();
    Nurse updateNurse(Long id, Nurse nurse);
    void deleteNurse(Long id);
}
