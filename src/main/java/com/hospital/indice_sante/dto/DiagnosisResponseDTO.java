package com.hospital.indice_sante.dto;

import java.time.LocalDateTime;
import java.util.List;

public class DiagnosisResponseDTO {

    private int healthIndex;
    private List<String> medicalUnits;
    private LocalDateTime createdAt;

    public DiagnosisResponseDTO(int healthIndex,
                                List<String> medicalUnits,
                                LocalDateTime createdAt) {
        this.healthIndex = healthIndex;
        this.medicalUnits = medicalUnits;
        this.createdAt = createdAt;
    }

    public int getHealthIndex() { return healthIndex; }
    public List<String> getMedicalUnits() { return medicalUnits; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}