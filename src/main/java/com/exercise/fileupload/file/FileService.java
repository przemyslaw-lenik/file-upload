package com.exercise.fileupload.file;

import com.exercise.fileupload.file.transport.FileCreateRequestDto;
import com.exercise.fileupload.file.transport.FileInfoDto;
import com.exercise.fileupload.file.transport.FileSearchResponseDto;
import com.exercise.fileupload.file.transport.TagRelatedDto;
import io.vavr.collection.List;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static java.util.function.Function.identity;

@AllArgsConstructor
@Service
class FileService {

    private final FileRepository fileRepository;
    private final TagRepository tagRepository;

    @Transactional
    public UUID createFile(FileCreateRequestDto request) {
        FileEntity fileEntity = new FileEntity(
                request.getName(),
                new FileBodyEntity(request.getBody())
        );

        findTagsAndSetReferences(request.getTags(), fileEntity);
        FileEntity insertedFileEntity = fileRepository.save(fileEntity);

        return insertedFileEntity.getId();
    }

    public FileSearchResponseDto searchFilesByTags(TagQuery searchQuery, int pageNumber, int size) {
        Page<FileEntity> fileEntities = findFilesLimitedByTags(searchQuery, pageNumber, size);

        List<TagRelatedDto> relatedTagsInfo = countRelatedFileTags(searchQuery, fileEntities);
        List<FileInfoDto> fileInfos = FileModelMapper.toFileInfoDto(fileEntities.getContent());

        return new FileSearchResponseDto(
            fileInfos,
                relatedTagsInfo,
                fileEntities.getTotalElements()
        );
    }

    private void findTagsAndSetReferences(List<String> tagNames, FileEntity fileEntity) {
        List<TagEntity> knownTags = List.ofAll(tagRepository.findByTagNameIn(tagNames));

        Set<String> knownTagNames = knownTags
                .map(TagEntity::getTagName)
                .toSet();

        Stream.ofAll(tagNames)
                .filter(tagName -> !knownTagNames.contains(tagName))
                .map(TagEntity::new)
                .prependAll(knownTags)
                .forEach(knownTag -> knownTag.addFile(fileEntity));
    }

    @Transactional(readOnly = true)
    Page<FileEntity> findFilesLimitedByTags(TagQuery searchQuery, int pageNumber, int size) {
        Set<String> tagNamesToInclude = searchQuery.getIncludingTagNames();
        List<UUID> fileIdsToNarrow = fileRepository
                .findIdsHavingAllTags(tagNamesToInclude, tagNamesToInclude.size());
        List<UUID> fileIdsToExclude = fileRepository
                .findIdsHavingAnyTag(searchQuery.getExcludingTagNames());
        PageRequest page = PageRequest.of(pageNumber, size, Sort.by("fileName"));
        return fileRepository.findByIdInAndIdNotIn(
                fileIdsToNarrow,
                fileIdsToExclude,
                page
        );
    }

    private List<TagRelatedDto> countRelatedFileTags(TagQuery searchQuery, Page<FileEntity> fileEntities) {
        Set<String> allTagNames = searchQuery.getAllTagNames();
        return Stream.ofAll(fileEntities.getContent())
                .flatMap(fileEntity -> Stream.ofAll(fileEntity.getFileTags())
                        .filter(tag -> !allTagNames.contains(tag.getTagName()))
                        .map(TagEntity::getTagName)
                )
                .groupBy(identity())
                .mapValues(Stream::length)
                .map(tagNameAndFileCount -> new TagRelatedDto(tagNameAndFileCount._1, tagNameAndFileCount._2))
                .toList();
    }

}
