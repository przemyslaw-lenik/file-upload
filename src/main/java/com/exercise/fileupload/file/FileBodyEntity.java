package com.exercise.fileupload.file;

import javax.persistence.*;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
@Entity
@Table(name = "file_body")
public class FileBodyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private byte[] body;

    public FileBodyEntity(byte[] body) {
        this.body = body;
    }
}
