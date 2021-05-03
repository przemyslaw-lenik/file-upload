package com.exercise.fileupload.file;

import io.vavr.collection.HashSet;
import io.vavr.collection.List;
import io.vavr.collection.Set;

public class TagQueryAssembler {

    private Set<String> includingTagNames = HashSet.empty();
    private Set<String> excludingTagNames = HashSet.empty();

    public TagQueryAssembler includingTagNames(String... includingTagNames) {
        this.includingTagNames = this.includingTagNames.addAll(List.of(includingTagNames));
        return this;
    }

    public TagQueryAssembler excludingTagNames(String... excludingTagNames) {
        this.excludingTagNames = this.excludingTagNames.addAll(List.of(excludingTagNames));
        return this;
    }

    public TagQuery assemble() {
        return new TagQuery(includingTagNames, excludingTagNames);
    }

}
