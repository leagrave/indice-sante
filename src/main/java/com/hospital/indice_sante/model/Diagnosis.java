package com.hospital.indice_sante.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Diagnosis {
    @Id
    @GeneratedValue
    private Long id;

    private int healthIndex;

    @ElementCollection
    private List<Pathology> pathologies;

    private LocalDateTime createdAt;

    @ManyToOne
    private Patient patient;
}
