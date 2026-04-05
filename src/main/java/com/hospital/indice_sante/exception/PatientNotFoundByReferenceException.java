package com.hospital.indice_sante.exception;

public class PatientNotFoundByReferenceException extends RuntimeException {

    public PatientNotFoundByReferenceException(String reference) {
        super("Patient not found with reference: " + reference);
    }
}