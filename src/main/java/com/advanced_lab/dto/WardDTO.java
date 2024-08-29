package com.advanced_lab.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WardDTO {
    private Long id;
    private Integer number;
    private Integer numberOfBeds;
    private Long departmentId;
    private Long supervisorId;
    private List<Long> hospitalizationIds;
}
