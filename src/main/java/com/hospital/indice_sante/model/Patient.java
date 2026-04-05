package com.hospital.indice_sante.model;

import jakarta.persistence.*;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true)
    private String nss;

    @Column(unique = true, nullable = false)
    private String reference;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // Contructeur vide pour JPA
    public Patient() {}

    // Getters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNss() {
        return nss;
    }

    public String getReference() {
        return reference;
    }

    public User getUser() {
        return user;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setUser(User user) {
        this.user= user;
    }
}
