package com.advanced_lab.iservices;

import com.advanced_lab.models.Hospitalization;
import java.util.List;

public interface HospitalizationService {
    Hospitalization createHospitalization(Hospitalization hospitalization);
    Hospitalization getHospitalizationById(String id);
    List<Hospitalization> getAllHospitalizations();
    Hospitalization updateHospitalization(String id, Hospitalization hospitalization);
    void deleteHospitalization(String id);
    List<Hospitalization> getHospitalizationsByPatient(String patientId);
    List<Hospitalization> getHospitalizationsByWard(String wardId);
    Hospitalization linkPatientToHospitalization(String hospitalizationId, String patientId);

}
