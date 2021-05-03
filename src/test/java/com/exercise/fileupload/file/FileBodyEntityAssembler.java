package com.exercise.fileupload.file;

public class FileBodyEntityAssembler {

    private Long id = null;
    private byte[] body = "".getBytes();

    public static FileBodyEntity fileBodyEntity() {
        return new FileBodyEntityAssembler()
                .assemble();
    }

    public static FileBodyEntity fileBodyEntity(Long id) {
        return new FileBodyEntityAssembler()
                .id(id)
                .assemble();
    }

    public FileBodyEntityAssembler id(Long id) {
        this.id = id;
        return this;
    }

    public FileBodyEntityAssembler body(byte[] body) {
        this.body = body;
        return this;
    }

    public FileBodyEntity assemble() {
        return new FileBodyEntity(id, body);
    }
}
