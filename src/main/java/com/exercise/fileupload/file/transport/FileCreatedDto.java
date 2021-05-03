package com.exercise.fileupload.file.transport;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class FileCreatedDto {
    @NotNull
    UUID uuid;

    @JsonCreator
    public FileCreatedDto(@NonNull UUID uuid) {
        this.uuid = uuid;
    }
}
