package com.hospital.indice_sante.dto;

public class CreatePatientDTO {

    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String nss;

    public CreatePatientDTO() {}

    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getNss() { return nss; }

    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setNss(String nss) { this.nss = nss; }
}