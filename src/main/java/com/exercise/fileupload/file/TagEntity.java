package com.exercise.fileupload.file;

import javax.persistence.*;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true)
@lombok.ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tag")
public class TagEntity {

    @lombok.EqualsAndHashCode.Include
    @lombok.ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String tagName;

    @Version
    @Column(nullable = false)
    private long version = 0L;

    @ManyToMany(mappedBy = "fileTags")
    private java.util.Set<FileEntity> taggedFiles = new java.util.HashSet<>();

    public TagEntity(String tagName) {
        this.tagName = tagName;
    }

    public void addFile(FileEntity fileEntity) {
        if (!this.taggedFiles.contains(fileEntity)) {
            this.taggedFiles.add(fileEntity);
            fileEntity.addTag(this);
        }
    }

}
