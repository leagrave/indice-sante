package com.hospital.indice_sante.service;

import com.hospital.indice_sante.exception.EmailAlreadyExistsException;
import com.hospital.indice_sante.model.*;
import com.hospital.indice_sante.repository.PatientRepository;
import com.hospital.indice_sante.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    private String generateReference() {
        return "PAT-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public Patient createPatient(String email, String password, String nom, String prenom, String nss) {
        validateInput(email, password, nom, prenom, nss);
        // création user
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.PATIENT);

        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException(email);
        }

        User savedUser = userRepository.save(user);

        // création patient
        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setNss(nss);
        patient.setReference(generateReference());
        patient.setUser(savedUser);

        return patientRepository.save(patient);
    }


    private void validateInput(String email, String password,
                               String nom, String prenom, String nss) {

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        if (password == null || password.length() < 6
                || !password.matches(".*[A-Za-z].*")
                || !password.matches(".*\\d.*")) {

            throw new RuntimeException("Password must be at least 6 characters with letters and numbers");
        }

        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Nom is required");
        }

        if (prenom == null || prenom.isBlank()) {
            throw new IllegalArgumentException("Prenom is required");
        }

        if (nss != null && !nss.isBlank()) {
            if (nss.length() < 10) {
                throw new RuntimeException("Invalid NSS format");
            }
        }
    }
}