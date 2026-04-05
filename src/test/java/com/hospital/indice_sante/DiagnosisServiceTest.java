package com.hospital.indice_sante;

import com.hospital.indice_sante.model.Pathology;
import com.hospital.indice_sante.service.DiagnosisService;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DiagnosisServiceTest {
    private final DiagnosisService diagnosisService = new DiagnosisService(null, null);

    @Test
    void should_detect_cardio_when_multiple_of_3() {
        Set<Pathology> result = diagnosisService.detectPathologies(3);
        assertTrue(result.contains(Pathology.CARDIAQUE));
    }

    @Test
    void should_detect_trauma_when_multiple_of_5() {
        Set<Pathology> result = diagnosisService.detectPathologies(5);
        assertTrue(result.contains(Pathology.FRACTURE));
    }

    @Test
    void should_detect_multiple_pathologies() {
        Set<Pathology> result = diagnosisService.detectPathologies(15);
        assertTrue(result.contains(Pathology.CARDIAQUE));
        assertTrue(result.contains(Pathology.FRACTURE));
    }
}