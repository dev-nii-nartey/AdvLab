package com.advanced_lab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long patientNumber;
    private String name;
    private String surname;
    private String address;
    private String telephoneNumber;
}
