package com.hospital.indice_sante.dto;

public class PatientResponseDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;

    public PatientResponseDTO(Long id, String nom, String prenom, String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
}