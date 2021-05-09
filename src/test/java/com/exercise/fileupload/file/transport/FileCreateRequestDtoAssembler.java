package com.exercise.fileupload.file.transport;

import io.vavr.collection.List;


public class FileCreateRequestDtoAssembler {

    String name = "document.txt";
    List<String> tags = List.empty();
    byte[] body = new byte[]{};

    public FileCreateRequestDtoAssembler name(String name) {
        this.name = name;
        return this;
    }

    public FileCreateRequestDtoAssembler tags(String... tags) {
        return this.tags(List.of(tags));
    }

    public FileCreateRequestDtoAssembler tags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public FileCreateRequestDtoAssembler nullBody() {
        return body(null);
    }

    public FileCreateRequestDtoAssembler body(byte[] body) {
        this.body = body;
        return this;
    }

    public FileCreateRequestDto assemble() {
        return new FileCreateRequestDto(name, tags, body);
    }

}