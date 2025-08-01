package com.gamic.Endurably.Gamic.dto;

import jakarta.validation.constraints.NotBlank;

public class RegistrationFieldDto {
    @NotBlank
    private String fieldName;
    @NotBlank
    private String fieldType;
    private boolean isRequired;


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }
}

