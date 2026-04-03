package com.hospital.indice_sante.dto;

public class CreateDiagnosisDTO {

    private Long patientId;
    private int healthIndex;

    public CreateDiagnosisDTO() {}

    public Long getPatientId() {
        return patientId;
    }

    public int getHealthIndex() {
        return healthIndex;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public void setHealthIndex(int healthIndex) {
        this.healthIndex = healthIndex;
    }
}