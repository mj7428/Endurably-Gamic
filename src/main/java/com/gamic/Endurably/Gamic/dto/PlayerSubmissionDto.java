package com.gamic.Endurably.Gamic.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;


public class PlayerSubmissionDto {

    @NotNull
    @NotEmpty
    private List<FieldValueDto> fieldValues;

    public List<FieldValueDto> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<FieldValueDto> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
