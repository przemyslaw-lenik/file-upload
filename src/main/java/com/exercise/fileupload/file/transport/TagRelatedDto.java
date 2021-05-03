package com.exercise.fileupload.file.transport;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class TagRelatedDto {

    @NotBlank
    String tagName;
    int fileCount;

    @JsonCreator
    public TagRelatedDto(@NonNull String tagName, int fileCount) {
        this.tagName = tagName;
        this.fileCount = fileCount;
    }
}
