package com.advanced_lab.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HospitalizationDTO {
    private Long id;
    private Integer bedNumber;
    private String diagnosis;
    private Long patientId;
    private Long wardId;
    private Long treatingDoctorId;
}

