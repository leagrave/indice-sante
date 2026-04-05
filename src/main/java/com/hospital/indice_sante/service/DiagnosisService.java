package com.hospital.indice_sante.service;

import com.hospital.indice_sante.dto.DiagnosisResponseDTO;
import com.hospital.indice_sante.exception.InvalidHealthIndexException;
import com.hospital.indice_sante.exception.PatientNotFoundException;
import com.hospital.indice_sante.model.*;
import com.hospital.indice_sante.repository.DiagnosisRepository;
import com.hospital.indice_sante.repository.PatientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public DiagnosisService(DiagnosisRepository diagnosisRepository,
                            PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.diagnosisRepository = diagnosisRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public DiagnosisResponseDTO analyze(int healthIndex, Long patientId) {

        validateHealthIndex(healthIndex);

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));

        Set<Pathology> pathologies = detectPathologies(healthIndex);
        Set<MedicalUnit> units = mapToMedicalUnits(pathologies);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setHealthIndex(healthIndex);
        diagnosis.setPathologies(new ArrayList<>(pathologies));
        diagnosis.setCreatedAt(LocalDateTime.now());
        diagnosis.setPatient(patient);

        diagnosisRepository.save(diagnosis);

        return new DiagnosisResponseDTO(
                healthIndex,
                units.stream().map(Enum::name).toList(),
                diagnosis.getCreatedAt()
        );
    }

    public List<DiagnosisResponseDTO> getDiagnosticsByPatient(Long patientId) {

        List<Diagnosis> diagnostics =
                diagnosisRepository.findByPatientIdOrderByCreatedAtDesc(patientId);

        return diagnostics.stream()
                .map(d -> new DiagnosisResponseDTO(
                        d.getHealthIndex(),
                        mapToMedicalUnits(new HashSet<>(d.getPathologies()))
                                .stream().map(Enum::name).toList(),
                        d.getCreatedAt()
                ))
                .toList();
    }

    public DiagnosisResponseDTO getLatestDiagnosis(Long patientId) {

        Diagnosis diagnosis = diagnosisRepository
                .findTopByPatientIdOrderByCreatedAtDesc(patientId)
                .orElseThrow(() -> new RuntimeException("No diagnosis found for patient " + patientId));

        return new DiagnosisResponseDTO(
                diagnosis.getHealthIndex(),
                mapToMedicalUnits(new HashSet<>(diagnosis.getPathologies()))
                        .stream().map(Enum::name).toList(),
                diagnosis.getCreatedAt()
        );
    }

    private Set<Pathology> detectPathologies(int healthIndex) {

        Set<Pathology> pathologies = new HashSet<>();

        // Cardio
        if (healthIndex % 3 == 0) pathologies.add(Pathology.CARDIAQUE);
        if (healthIndex % 6 == 0) pathologies.add(Pathology.HYPERTENSION);
        if (healthIndex % 9 == 0) pathologies.add(Pathology.ARYTHMIE);

        // Trauma
        if (healthIndex % 5 == 0) pathologies.add(Pathology.FRACTURE);
        if (healthIndex % 10 == 0) pathologies.add(Pathology.ENTORSE);
        if (healthIndex % 20 == 0) pathologies.add(Pathology.LUXATION);

        // Neuro
        if (healthIndex % 7 == 0) pathologies.add(Pathology.MIGRAINE);
        if (healthIndex % 14 == 0) pathologies.add(Pathology.AVC);
        if (healthIndex % 21 == 0) pathologies.add(Pathology.EPILEPSIE);

        // Infection
        if (healthIndex % 11 == 0) pathologies.add(Pathology.INFECTION_VIRALE);
        if (healthIndex % 13 == 0) pathologies.add(Pathology.INFECTION_BACTERIENNE);
        if (healthIndex % 17 == 0) pathologies.add(Pathology.FIEVRE);

        // Respiratoire
        if (healthIndex % 19 == 0) pathologies.add(Pathology.ASTHME);
        if (healthIndex % 23 == 0) pathologies.add(Pathology.PNEUMONIE);

        // Cancer
        if (healthIndex % 29 == 0) pathologies.add(Pathology.CANCER_POUMON);
        if (healthIndex % 31 == 0) pathologies.add(Pathology.CANCER_SEIN);
        if (healthIndex % 37 == 0) pathologies.add(Pathology.TUMEUR);

        // Général
        if (healthIndex % 2 == 0) pathologies.add(Pathology.FATIGUE);
        if (healthIndex % 4 == 0) pathologies.add(Pathology.DOULEUR);
        if (healthIndex % 8 == 0) pathologies.add(Pathology.TROUBLE_GENERAL);

        return pathologies;
    }

    // Mapping unités médicales
    private Set<MedicalUnit> mapToMedicalUnits(Set<Pathology> pathologies) {
        Set<MedicalUnit> units = Arrays.stream(MedicalUnit.values())
                .filter(unit -> pathologies.stream()
                        .anyMatch(p -> unit.getPathologies().contains(p)))
                .collect(Collectors.toSet());
        if (units.isEmpty()) {
            units.add(MedicalUnit.GENERAL);
        }
        return units;
    }

    // Validation
    private void validateHealthIndex(int healthIndex) {

        if (healthIndex <= 0) {
            throw new InvalidHealthIndexException("Health index must be greater than 0");
        }

        if (healthIndex > 1_000_000) {
            throw new InvalidHealthIndexException("Health index too large");
        }
    }

}
