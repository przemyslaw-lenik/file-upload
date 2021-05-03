package com.exercise.fileupload.file;

import io.vavr.collection.List;
import io.vavr.collection.Traversable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TagRepository extends CrudRepository<TagEntity, Long> {

    @EntityGraph(attributePaths = {"taggedFiles"})
    List<TagEntity> findByTagNameIn(Traversable<String> names);
}
