package com.hospital.indice_sante.model;

import java.util.List;

public enum MedicalUnit {

    CARDIOLOGIE(List.of(Pathology.HYPERTENSION,Pathology.ARYTHMIE,Pathology.CARDIAQUE)),
    TRAUMATOLOGIE(List.of(Pathology.FRACTURE,Pathology.ENTORSE,Pathology.LUXATION)),
    NEUROLOGIE(List.of(Pathology.AVC,Pathology.MIGRAINE,Pathology.EPILEPSIE)),
    INFECTION(List.of(Pathology.INFECTION_VIRALE,Pathology.INFECTION_BACTERIENNE,Pathology.FIEVRE)),
    RESPIRATOIRE(List.of(Pathology.ASTHME,Pathology.PNEUMONIE)),
    CANCEROLOGIE(List.of(Pathology.CANCER_POUMON,Pathology.CANCER_SEIN,Pathology.TUMEUR)),
    GENERAL(List.of(Pathology.FATIGUE,Pathology.DOULEUR,Pathology.TROUBLE_GENERAL));

    private final List<Pathology> pathologies;

    MedicalUnit(List<Pathology> pathologies) {
        this.pathologies = pathologies;
    }

    public List<Pathology> getPathologies() {
        return pathologies;
    }
}