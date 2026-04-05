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

    @Column(nullable = false)
    private int healthIndex;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Pathology> pathologies;

    private LocalDateTime createdAt;

    @ManyToOne
    private Patient patient;

    public Diagnosis() {}

    public Long getId() {
        return id;
    }

    public int getHealthIndex() {
        return healthIndex;
    }

    public void setHealthIndex(int healthIndex) {
        this.healthIndex = healthIndex;
    }

    public List<Pathology> getPathologies() {
        return pathologies;
    }

    public void setPathologies(List<Pathology> pathologies) {
        this.pathologies = pathologies;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
