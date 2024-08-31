package com.advanced_lab.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDTO {
    private String id;
    private String code;
    private String name;
    private String building;
    private DoctorDTO director;
    private List<WardDTO> wards;
    private List<NurseDTO> nurses;

}
