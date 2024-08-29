package com.advanced_lab.models;

import com.advanced_lab.models.Department;
import com.advanced_lab.models.Employee;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "nurses")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Nurse")
public class Nurse extends Employee {
    private String rotation;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne(mappedBy = "supervisor")
    private Ward supervisedWard;
}
