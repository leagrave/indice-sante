package com.hospital.indice_sante.dto;

public class PatientResponseDTO {

    private Long id;
    private String reference;
    private String nom;
    private String prenom;
    private String email;

    public PatientResponseDTO(Long id,String reference, String nom, String prenom, String email) {
        this.id = id;
        this.reference = reference;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getReference() { return reference; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
}