package com.advanced_lab.services;

import com.advanced_lab.iservices.NurseService;
import com.advanced_lab.models.Nurse;
import com.advanced_lab.repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {

    private final NurseRepository nurseRepository;

    public NurseServiceImpl(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @Override
    public Nurse createNurse(Nurse nurse) {

        return nurseRepository.save(nurse);
    }

    @Override
    public Nurse getNurseById(Long id) {
        return nurseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nurse not found with id: " + id));
    }

    @Override
    public List<Nurse> getAllNurses() {
        return nurseRepository.findAll();
    }

    @Override
    public Nurse updateNurse(Long id, Nurse nurse) {
        Nurse existingNurse = getNurseById(id);
        existingNurse.setSurname(nurse.getSurname());
        existingNurse.setFirstName(nurse.getFirstName());
        existingNurse.setAddress(nurse.getAddress());
        existingNurse.setTelephoneNumber(nurse.getTelephoneNumber());
        existingNurse.setSalary(nurse.getSalary());
        existingNurse.setRotation(nurse.getRotation());
        existingNurse.setDepartment(nurse.getDepartment());
        existingNurse.setSupervisedWard(nurse.getSupervisedWard());
        return nurseRepository.save(existingNurse);
    }

    @Override
    public void deleteNurse(Long id) {
        nurseRepository.deleteById(id);
    }
}
