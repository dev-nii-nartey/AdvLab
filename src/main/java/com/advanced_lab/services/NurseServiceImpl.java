package com.advanced_lab.services;

import com.advanced_lab.iservices.NurseService;
import com.advanced_lab.models.Nurse;
import com.advanced_lab.repositories.NurseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServiceImpl implements NurseService {

    private static final Logger logger = LoggerFactory.getLogger(NurseServiceImpl.class);
    private final NurseRepository nurseRepository;

    @Autowired
    public NurseServiceImpl(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    @Override
    @CacheEvict(value = {"nurseCache", "allNursesCache"}, allEntries = true)
    public Nurse createNurse(Nurse nurse) {
        nurse.setEmployeeType("Nurse");
        return nurseRepository.save(nurse);
    }

    @Override
    @Cacheable(value = "nurseCache", key = "#nurseId")
    public Nurse getNurseById(String nurseId) {
        return nurseRepository.findByIdAndEmployeeTypeAndIsDeletedFalse(nurseId, "Nurse")
                .orElseThrow(() -> new RuntimeException("Nurse not found with id: " + nurseId));
    }

    @Override
    @Cacheable(value = "allNursesCache", unless = "#result.isEmpty()")
    public List<Nurse> getAllNurses() {
        return nurseRepository.findByEmployeeTypeAndIsDeletedFalse("Nurse");
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "nurseCache", key = "#nurseId"),
            @CacheEvict(value = "allNursesCache", allEntries = true)
    })
    public Nurse updateNurse(String nurseId, Nurse nurse) {
        Nurse existingNurse = getNurseById(nurseId);
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
    @Caching(evict = {
            @CacheEvict(value = "nurseCache", key = "#id"),
            @CacheEvict(value = "allNursesCache", allEntries = true)
    })
    public void deleteNurse(String id) {
        Nurse nurse = getNurseById(id);
        nurse.setDeleted(true);
        nurseRepository.save(nurse);
    }
}
