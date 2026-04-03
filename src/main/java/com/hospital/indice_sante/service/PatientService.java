package com.hospital.indice_sante.service;

import com.hospital.indice_sante.model.*;
import com.hospital.indice_sante.repository.PatientRepository;
import com.hospital.indice_sante.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public Patient createPatient(String email, String password, String nom, String prenom, String nss) {
        validateInput(email, password, nom, prenom, nss);
        // création user
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Role.PATIENT);

        User savedUser = userRepository.save(user);

        // création patient
        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setNss(nss);
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

        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Password too short");
        }

        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Nom is required");
        }

        if (prenom == null || prenom.isBlank()) {
            throw new IllegalArgumentException("Prenom is required");
        }

        if (nss == null || nss.length() < 5) {
            throw new IllegalArgumentException("Invalid NSS");
        }
    }
}