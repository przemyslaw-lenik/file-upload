package com.exercise.fileupload.file;

import com.exercise.fileupload.file.transport.FileInfoDto;
import io.vavr.collection.List;
import lombok.experimental.UtilityClass;

@UtilityClass
class FileModelMapper {

    public static List<FileInfoDto> toFileInfoDto(Iterable<FileEntity> fileEntities) {
        return toFileInfoDto(List.ofAll(fileEntities));
    }

    public static List<FileInfoDto> toFileInfoDto(List<FileEntity> fileEntities) {
        return fileEntities
                .map(fileEntity -> new FileInfoDto(fileEntity.getId(), fileEntity.getFileName()));
    }
}
