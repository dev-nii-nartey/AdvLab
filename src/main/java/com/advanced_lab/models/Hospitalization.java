package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospitalizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hospitalization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer bedNumber;
    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor treatingDoctor;
}
