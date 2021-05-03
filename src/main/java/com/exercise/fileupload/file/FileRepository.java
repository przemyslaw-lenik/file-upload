package com.exercise.fileupload.file;

import io.vavr.collection.List;
import io.vavr.collection.Traversable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface FileRepository extends PagingAndSortingRepository<FileEntity, UUID> {

    @Query("select distinct file.id from FileEntity file " +
            " join file.fileTags tag " +
            " where tag.tagName in :tagNames " +
            " group by file.id " +
            " having count(tag.id) = :tagsSize "
    )
    List<UUID> findIdsHavingAllTags(
            @Param("tagNames") Traversable<String> tagNames,
            @Param("tagsSize") long tagsSize
    );

    @Query("select distinct file.id from FileEntity file " +
            " join file.fileTags tag " +
            " where (tag.tagName in :tagNames) "
    )
    List<UUID> findIdsHavingAnyTag(@Param("tagNames") Traversable<String> tagNames);

    @EntityGraph(attributePaths = {"fileTags"})
    Page<FileEntity> findByIdInAndIdNotIn(
            Traversable<UUID> includingIds,
            Traversable<UUID> excludingIds,
            Pageable pageable
    );
}
