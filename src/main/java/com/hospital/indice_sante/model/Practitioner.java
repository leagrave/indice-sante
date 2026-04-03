package com.hospital.indice_sante.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Practitioner {
    @Id
    @GeneratedValue
    private Long id;

    private String nom;
    private String prenom;

    private String rule;

    @OneToOne
    private User user;
}
