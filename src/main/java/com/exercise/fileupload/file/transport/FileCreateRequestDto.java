package com.exercise.fileupload.file.transport;

import com.exercise.fileupload.file.TagListPattern;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.vavr.collection.List;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class FileCreateRequestDto {

    @NotBlank
    String name;
    @TagListPattern
    List<String> tags;
    @NotNull
    byte[] body;

    @JsonCreator
    public FileCreateRequestDto(String name, List<String> tags, byte[] body) {
        this.name = name;
        this.tags = tags == null
                ? List.empty()
                : tags;
        this.body = body;
    }
}
