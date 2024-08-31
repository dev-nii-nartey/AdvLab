package com.advanced_lab.iservices;

import com.advanced_lab.models.Nurse;
import java.util.List;

public interface NurseService {
    Nurse createNurse(Nurse nurse);
    Nurse getNurseById(String id);
    List<Nurse> getAllNurses();
    Nurse updateNurse(String id, Nurse nurse);
    void deleteNurse(String id);
}
