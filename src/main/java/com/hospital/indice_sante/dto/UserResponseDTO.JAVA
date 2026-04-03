package com.hospital.indice_sante.dto;

public class UserResponseDTO {

    private String email;

    public UserResponseDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}

