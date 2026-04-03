package com.hospital.indice_sante.repository;

import com.hospital.indice_sante.model.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    List<Diagnosis> findByPatientIdOrderByCreatedAtDesc(Long patientId);
    Optional<Diagnosis> findTopByPatientIdOrderByCreatedAtDesc(Long patientId);

}