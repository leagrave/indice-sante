package com.hospital.indice_sante.controller;

import com.hospital.indice_sante.dto.CreatePatientDTO;
import com.hospital.indice_sante.dto.PatientResponseDTO;
import com.hospital.indice_sante.exception.PatientNotFoundException;
import com.hospital.indice_sante.model.Patient;
import com.hospital.indice_sante.repository.PatientRepository;
import com.hospital.indice_sante.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public PatientController(PatientService patientService,
                             PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    // POST
    @PostMapping
    public PatientResponseDTO createPatient(@RequestBody CreatePatientDTO dto) {
        Patient patient = patientService.createPatient(
                dto.getEmail(),
                dto.getPassword(),
                dto.getNom(),
                dto.getPrenom(),
                dto.getNss()
        );

        return mapToDTO(patient);
    }

    // GET ALL
    @GetMapping
    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PatientResponseDTO getPatientById(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        return mapToDTO(patient);
    }

    // mapping
    private PatientResponseDTO mapToDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getNom(),
                patient.getPrenom(),
                patient.getUser().getEmail()
        );
    }
}