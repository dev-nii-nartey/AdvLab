package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "wards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private Integer numberOfBeds;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToOne
    @JoinColumn(name = "supervisor_id")
    private Nurse supervisor;

    @OneToMany(mappedBy = "ward")
    private List<Hospitalization> hospitalizations;
}
