package com.exercise.fileupload.file;

import io.vavr.collection.List;

import java.util.Arrays;

public class TagEntityAssembler {

    private Long id = null;
    private String tagName = "tag1";
    private long version = 0L;
    private java.util.Set<FileEntity> taggedFiles = new java.util.HashSet<>();

    public TagEntityAssembler() {
    }

    public static TagEntity tagEntity(String name) {
        return new TagEntityAssembler()
                .tagName(name)
                .assemble();
    }

    public TagEntityAssembler id(Long id) {
        this.id = id;
        return this;
    }

    public TagEntityAssembler tagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public TagEntityAssembler version(long version) {
        this.version = version;
        return this;
    }

    public TagEntityAssembler addTaggedFiles(FileEntity... taggedFiles) {
        this.taggedFiles.addAll(Arrays.asList(taggedFiles));
        return this;
    }

    public TagEntityAssembler taggedFiles(FileEntity... taggedFiles) {
        return taggedFiles(List.of(taggedFiles).toJavaSet());
    }

    public TagEntityAssembler taggedFiles(java.util.Set<FileEntity> taggedFiles) {
        this.taggedFiles = taggedFiles;
        return this;
    }

    public TagEntity assemble() {
        return new TagEntity(
                id,
                tagName,
                version,
                taggedFiles
        );
    }
}