package com.exercise.fileupload.file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class FileRepositoryIT {

    @Autowired
    private FileRepository repository;

    @Test
    void shouldPersistFileEntityAlongWithChildren() {
        // given
        TagEntity tag = new TagEntityAssembler().assemble();
        FileEntity fileEntity = new FileEntityAssembler()
                .fileTags(tag)
                .assemble();

        tag.addFile(fileEntity);

        // when then
        assertThatCode(() -> repository.save(fileEntity))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldRetrieveOnlyMatchingFiles() {
        TagEntity tag1 = new TagEntityAssembler()
                .tagName("Tag1")
                .version(2)
                .assemble();
        TagEntity tag2 = new TagEntityAssembler()
                .tagName("Tag2")
                .assemble();
        TagEntity tag3 = new TagEntityAssembler()
                .tagName("Tag3")
                .assemble();
        TagEntity tag4 = new TagEntityAssembler()
                .tagName("Tag4")
                .assemble();
        TagEntity tag5 = new TagEntityAssembler()
                .tagName("Tag5")
                .assemble();
    }
}