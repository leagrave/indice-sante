package com.hospital.indice_sante.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handlePatientNotFound(PatientNotFoundException ex) {
        return buildResponse(ex.getMessage(), 404);
    }

    @ExceptionHandler(InvalidHealthIndexException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleInvalidIndex(InvalidHealthIndexException ex) {
        return buildResponse(ex.getMessage(), 400);
    }

    private Map<String, Object> buildResponse(String message, int status) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", status);
        error.put("message", message);
        return error;
    }


}
