package com.hospital.indice_sante.repository;

import com.hospital.indice_sante.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}