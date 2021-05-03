package com.exercise.fileupload.file;

import com.exercise.fileupload.file.transport.FileCreateRequestDto;
import com.exercise.fileupload.file.transport.FileInfoDto;
import com.exercise.fileupload.file.transport.FileSearchResponseDto;
import com.exercise.fileupload.file.transport.TagRelatedDto;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "/db/file_tags_setup.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class FileServiceIT {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private TagRepository tagRepository;

    @Test
    void shouldInsertFileWithProperTagRelations() {
        // given
        List<String> givenTags = List.of("Tag1", "Tag14");
        FileCreateRequestDto request = new FileCreateRequestDto(
                "document.txt",
                givenTags,
                "".getBytes()
        );

        // when
        UUID id = fileService.createFile(request);

        // then
        assertThat(id)
                .isNotNull();
        assertThat(tagRepository.findAll())
                .hasSize(6);
        assertThat(fileRepository.findById(id))
                .isPresent();
    }

    @Test
    void shouldFindOnlyFilesWithExactTags() {
        // given
        TagQuery givenSearchQuery = new TagQueryAssembler()
                .includingTagNames("Tag2", "Tag3")
                .excludingTagNames("Tag4")
                .assemble();

        List<TagRelatedDto> expectedTagsRelated = List.of(
                new TagRelatedDto("Tag1", 1),
                new TagRelatedDto("Tag5", 2)
        );

        // when
        FileSearchResponseDto actual = fileService.searchFilesByTags(givenSearchQuery, 0, 20);

        // then
        assertThat(actual.getTotalRecords())
                .isEqualTo(2);
        assertThat(actual.getFiles())
                .extracting(FileInfoDto::getName)
                .containsExactlyInAnyOrder("File1", "File3");
        assertThat(actual.getRelatedTags())
                .containsExactlyInAnyOrderElementsOf(expectedTagsRelated);
    }
}