package com.advanced_lab.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Employee {
    @Id
    private String id;

    private String surname;
    private String firstName;
    private String address;
    private String telephoneNumber;
    private Double salary;
    private boolean isDeleted;
    private String employeeType;  // To differentiate between Doctor and Nurse

}
