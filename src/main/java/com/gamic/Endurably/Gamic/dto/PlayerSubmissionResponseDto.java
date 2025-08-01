package com.gamic.Endurably.Gamic.dto;

import java.util.List;

public class PlayerSubmissionResponseDto {

    private Long playerSubmissionId;
    private List<FieldValueResponseDto> fieldValues;


    public Long getPlayerSubmissionId() {
        return playerSubmissionId;
    }

    public void setPlayerSubmissionId(Long playerSubmissionId) {
        this.playerSubmissionId = playerSubmissionId;
    }

    public List<FieldValueResponseDto> getFieldValues() {
        return fieldValues;
    }

    public void setFieldValues(List<FieldValueResponseDto> fieldValues) {
        this.fieldValues = fieldValues;
    }
}
