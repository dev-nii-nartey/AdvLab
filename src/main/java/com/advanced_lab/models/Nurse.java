package com.advanced_lab.models;

import com.advanced_lab.models.Department;
import com.advanced_lab.models.Employee;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "nurses")
@Data
@SuperBuilder
@AllArgsConstructor
public class Nurse extends Employee {
    private String rotation;

    @DBRef(lazy = true)
    private Department department;

    @DBRef(lazy = true)
    private Ward supervisedWard;

    public Nurse() {
        super.setEmployeeType("Nurse");
    }
}
