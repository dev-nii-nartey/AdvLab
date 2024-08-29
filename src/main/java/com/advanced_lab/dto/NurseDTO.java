package com.advanced_lab.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class NurseDTO extends EmployeeDTO {
    private String rotation;
    private Long departmentId;
}
