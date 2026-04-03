package com.hospital.indice_sante.controller;

import com.hospital.indice_sante.dto.CreateDiagnosisDTO;
import com.hospital.indice_sante.dto.DiagnosisResponseDTO;
import com.hospital.indice_sante.service.DiagnosisService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/diagnostic")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @PostMapping
    public DiagnosisResponseDTO createDiagnostic(@RequestBody CreateDiagnosisDTO dto) {

        return diagnosisService.analyze(
                dto.getHealthIndex(),
                dto.getPatientId()
        );
    }

    @Operation(summary = "Récupération des diagnostics d’un patient")
    @GetMapping("/{patientId}")
    public List<DiagnosisResponseDTO> getDiagnostics(@PathVariable Long patientId) {
        return diagnosisService.getDiagnosticsByPatient(patientId);
    }

    @Operation(summary = "Récupérer le dernier diagnostic d’un patient")
    @GetMapping("/{patientId}/latest")
    public DiagnosisResponseDTO getLatestDiagnosis(@PathVariable Long patientId) {
        return diagnosisService.getLatestDiagnosis(patientId);
    }
}
