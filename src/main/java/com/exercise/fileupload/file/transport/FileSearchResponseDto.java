package com.exercise.fileupload.file.transport;

import io.vavr.collection.List;
import lombok.NonNull;
import lombok.Value;

@Value
public class FileSearchResponseDto {
    @NonNull
    List<FileInfoDto> files;
    @NonNull
    List<TagRelatedDto> relatedTags;
    long totalRecords;
}
