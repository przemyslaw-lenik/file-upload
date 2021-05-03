package com.exercise.fileupload.file.transport;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.NonNull;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class FileInfoDto {
    @NotNull
    UUID uuid;
    @NotNull
    String name;

    @JsonCreator
    public FileInfoDto(@NonNull UUID uuid, @NonNull String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
