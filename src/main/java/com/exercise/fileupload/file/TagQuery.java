package com.exercise.fileupload.file;

import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import lombok.NonNull;
import lombok.Value;

@Value
class TagQuery {
    @NonNull
    Set<String> includingTagNames;
    @NonNull
    Set<String> excludingTagNames;

    public static TagQuery fromString(String tagSearchQuery) {
        return Stream.of(tagSearchQuery.split(" "))
                .filter(tag -> !tag.isEmpty())
                .partition(tagName -> tagName.startsWith("+"))
                .apply((includingTags, excludingTags) -> new TagQuery(
                                removeFirstChar(includingTags),
                                removeFirstChar(excludingTags)
                        )
                );
    }

    private static Set<String> removeFirstChar(Stream<String> prefixedTagNames) {
        return prefixedTagNames
                .map(prefixedTagName -> prefixedTagName.substring(1))
                .toSet();
    }

    public Set<String> getAllTagNames() {
        return includingTagNames.addAll(excludingTagNames);
    }
}
