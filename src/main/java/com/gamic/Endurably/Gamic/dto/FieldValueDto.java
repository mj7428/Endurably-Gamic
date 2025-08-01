package com.gamic.Endurably.Gamic.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FieldValueDto {

    @NotNull
    private Long fieldDefinitionId;

    @NotEmpty
    private String value; 

    public Long getFieldDefinitionId() {
        return fieldDefinitionId;
    }

    public void setFieldDefinitionId(Long fieldDefinitionId) {
        this.fieldDefinitionId = fieldDefinitionId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
