package com.advanced_lab.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Doctor")
public class Doctor extends Employee {
    private String specialty;

    @OneToOne(mappedBy = "director")
    private Department department;
}
