package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeNumber;

    private String surname;
    private String firstName;
    private String address;
    private String telephoneNumber;
}
