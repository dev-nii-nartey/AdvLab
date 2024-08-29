package com.advanced_lab.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "doctors")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Doctor")
public class Doctor extends Employee {
    private String specialty;

    @OneToOne(mappedBy = "director")
    private Department department;
}
