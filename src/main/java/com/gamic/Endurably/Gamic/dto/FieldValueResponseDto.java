package com.gamic.Endurably.Gamic.dto;


public class FieldValueResponseDto {

    private String fieldName; // The question (e.g., "Clan Tag")
    private String value;     // The answer (e.g., "#ABC123XYZ")


    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
