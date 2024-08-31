package com.advanced_lab.iservices;

import com.advanced_lab.models.Hospitalization;
import java.util.List;

public interface HospitalizationService {
    Hospitalization createHospitalization(Hospitalization hospitalization);
    Hospitalization getHospitalizationById(Long id);
    List<Hospitalization> getAllHospitalizations();
    Hospitalization updateHospitalization(Long id, Hospitalization hospitalization);
    void deleteHospitalization(Long id);
    List<Hospitalization> getHospitalizationsByPatient(Long patientId);
    List<Hospitalization> getHospitalizationsByWard(Long wardId);
    Hospitalization linkPatientToHospitalization(Long hospitalizationId, Long patientId);

}
