package com.exercise.fileupload.file;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(onlyExplicitlyIncluded = true)
@lombok.ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "file")
public class FileEntity {

    @lombok.EqualsAndHashCode.Include
    @lombok.ToString.Include
    @Id
    @GeneratedValue(generator = "UUID")
    @org.hibernate.annotations.GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(columnDefinition = "uuid", nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, updatable = false)
    private String fileName;

    @Version
    @Column(nullable = false)
    private long version = 0L;

    @OneToOne(
            optional = false,
            fetch = FetchType.LAZY,
            orphanRemoval = true,
            cascade = { CascadeType.PERSIST, CascadeType.REMOVE }
    )
    @JoinColumn(name = "file_body_id", updatable = false)
    private FileBodyEntity body;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "file_tag",
            joinColumns = @JoinColumn(name = "file_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private java.util.Set<TagEntity> fileTags = new java.util.HashSet<>();

    public FileEntity(String fileName, FileBodyEntity body) {
        this.fileName = fileName;
        this.body = body;
    }

    public void addTags(Collection<TagEntity> tagEntities) {
        tagEntities.forEach(this::addTag);
    }

    public void addTag(TagEntity tag) {
        if (!this.fileTags.contains(tag)) {
            this.fileTags.add(tag);
            tag.addFile(this);
        }
    }

}
