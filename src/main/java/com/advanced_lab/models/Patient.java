package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientNumber;

    private String name;
    private String surname;
    private String address;
    private String telephoneNumber;

    @OneToMany(mappedBy = "patient")
    private List<Hospitalization> hospitalizations;
}
