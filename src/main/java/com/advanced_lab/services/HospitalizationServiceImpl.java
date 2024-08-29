package com.advanced_lab.services;

import com.advanced_lab.iservices.HospitalizationService;
import com.advanced_lab.models.Hospitalization;
import com.advanced_lab.models.Patient;
import com.advanced_lab.repositories.HospitalizationRepository;
import com.advanced_lab.repositories.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalizationServiceImpl implements HospitalizationService {

    private final HospitalizationRepository hospitalizationRepository;
    private final PatientRepository patientRepository;


    public HospitalizationServiceImpl(HospitalizationRepository hospitalizationRepository, PatientRepository patientRepository) {
        this.hospitalizationRepository = hospitalizationRepository;
        this.patientRepository = patientRepository;

    }

    @Override
    public Hospitalization createHospitalization(Hospitalization hospitalization) {
        return hospitalizationRepository.save(hospitalization);
    }

    @Override
    public Hospitalization getHospitalizationById(Long id) {
        return hospitalizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hospitalization not found with id: " + id));
    }

    @Override
    public List<Hospitalization> getAllHospitalizations() {
        return hospitalizationRepository.findAll();
    }

    @Override
    public Hospitalization updateHospitalization(Long id, Hospitalization hospitalization) {
        Hospitalization existingHospitalization = getHospitalizationById(id);
        existingHospitalization.setBedNumber(hospitalization.getBedNumber());
        existingHospitalization.setDiagnosis(hospitalization.getDiagnosis());
        existingHospitalization.setPatient(hospitalization.getPatient());
        existingHospitalization.setWard(hospitalization.getWard());
        existingHospitalization.setTreatingDoctor(hospitalization.getTreatingDoctor());
        return hospitalizationRepository.save(existingHospitalization);
    }

    @Override
    public void deleteHospitalization(Long id) {
        hospitalizationRepository.deleteById(id);
    }

    @Override
    public List<Hospitalization> getHospitalizationsByPatient(Long patientId) {
        return hospitalizationRepository.findByPatientPatientNumber(patientId);
    }

    @Override
    public List<Hospitalization> getHospitalizationsByWard(Long wardId) {
        return hospitalizationRepository.findByWardId(wardId);
    }

    @Override
    @Transactional
    public Hospitalization linkPatientToHospitalization(Long hospitalizationId, Long patientId) {
        Hospitalization hospitalization = hospitalizationRepository.findById(hospitalizationId)
                .orElseThrow(() -> new EntityNotFoundException("Hospitalization not found with id: " + hospitalizationId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));

        hospitalization.setPatient(patient);
        return hospitalizationRepository.save(hospitalization);
    }
}
