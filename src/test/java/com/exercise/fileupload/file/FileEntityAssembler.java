package com.exercise.fileupload.file;

import io.vavr.collection.List;
import io.vavr.collection.Traversable;

import java.util.Arrays;
import java.util.UUID;

public class FileEntityAssembler {

    private UUID id = null;
    private String fileName = "document.txt";
    private long version = 0L;
    private FileBodyEntity body = FileBodyEntityAssembler.fileBodyEntity();
    private java.util.Set<TagEntity> fileTags = new java.util.HashSet<>();

    public FileEntityAssembler id(UUID id) {
        this.id = id;
        return this;
    }

    public FileEntityAssembler fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public FileEntityAssembler version(long version) {
        this.version = version;
        return this;
    }

    public FileEntityAssembler body(FileBodyEntity body) {
        this.body = body;
        return this;
    }

    public FileEntityAssembler addFileTags(TagEntity... fileTags) {
        this.fileTags.addAll(Arrays.asList(fileTags));
        return this;
    }

    public FileEntityAssembler fileTags(TagEntity... fileTags) {
        return fileTags(List.of(fileTags).toJavaSet());
    }

    public FileEntityAssembler fileTags(Traversable<TagEntity> fileTags) {
        return fileTags(fileTags.toJavaSet());
    }

    public FileEntityAssembler fileTags(java.util.Set<TagEntity> fileTags) {
        this.fileTags = fileTags;
        return this;
    }

    public FileEntity assemble() {
        return new FileEntity(id, fileName, version, body, fileTags);
    }
}