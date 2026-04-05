package com.hospital.indice_sante.dto;

public class MetaDataItemDTO {

    private String code;
    private String label;

    public MetaDataItemDTO(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }
}