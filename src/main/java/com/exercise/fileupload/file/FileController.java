package com.exercise.fileupload.file;

import com.exercise.fileupload.file.transport.FileCreateRequestDto;
import com.exercise.fileupload.file.transport.FileCreatedDto;
import com.exercise.fileupload.file.transport.FileSearchResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.UUID;

@AllArgsConstructor
@RestController
@Validated
class FileController {

    private final FileService fileService;

    @PostMapping("/file")
    public FileCreatedDto insertFile(@RequestBody FileCreateRequestDto request) {
        UUID persistedFileId = fileService.createFile(request);
        return new FileCreatedDto(persistedFileId);
    }

    @GetMapping("/files/{tagSearchQuery}/page")
    public FileSearchResponseDto searchFiles(
            @PathVariable(name = "tagSearchQuery") @TagSearchQueryConstraint String tagSearchQuery,
            @RequestParam("page") @Min(0) int page,
            @RequestParam("size") @Min(5) @Max(100) int size
    ) {
        TagQuery searchQuery = TagQuery.fromString(tagSearchQuery);
        return fileService.searchFilesByTags(searchQuery, page, size);
    }

}
