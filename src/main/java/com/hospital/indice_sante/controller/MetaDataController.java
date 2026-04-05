package com.hospital.indice_sante.controller;

import com.hospital.indice_sante.model.MedicalUnit;
import com.hospital.indice_sante.model.Pathology;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/metadata")
public class MetaDataController {

    // Liste des pathologies
    @Operation(summary = "Récupération de toutes les pathologies")
    @GetMapping("/pathologies")
    public List<String> getPathologies() {
        return Arrays.stream(Pathology.values())
                .map(Enum::name)
                .toList();
    }

    // Liste des unités médicales
    @Operation(summary = "Récupération de toutes les unité médicales")
    @GetMapping("/medical-units")
    public List<String> getMedicalUnits() {
        return Arrays.stream(MedicalUnit.values())
                .map(Enum::name)
                .toList();
    }

    // Mapping unit → pathologies
    @Operation(summary = "Récupération de toutes les unités médical et leurs pathiologies")
    @GetMapping("/medical-units/details")
    public Map<String, List<String>> getMedicalUnitsWithPathologies() {
        return Arrays.stream(MedicalUnit.values())
                .collect(Collectors.toMap(
                        MedicalUnit::name,
                        unit -> unit.getPathologies()
                                .stream()
                                .map(Enum::name)
                                .toList()
                ));
    }

    // helper label propre
    private String formatLabel(String value) {
        return value.replace("_", " ").toLowerCase();
    }
}