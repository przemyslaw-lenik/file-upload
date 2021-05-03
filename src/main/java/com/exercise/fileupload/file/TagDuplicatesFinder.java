package com.exercise.fileupload.file;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import lombok.Value;
import lombok.With;

class TagDuplicatesFinder {

    public Set<String> findDuplicateTags(Stream<String> tagsWithoutBlanks) {
        return tagsWithoutBlanks
                .map(tagWithLeadingChar -> tagWithLeadingChar.substring(1))
                .foldLeft(DuplicatesAccumulator.empty(), (accumulator, tag) -> accumulator.isElementSeen(tag)
                        ? accumulator.addDuplicate(tag)
                        : accumulator.addSeenElement(tag)
                )
                .getDuplicates();
    }

    @Value
    private static class DuplicatesAccumulator {
        @With
        Set<String> seenElements;
        @With
        Set<String> duplicates;

        public static DuplicatesAccumulator empty() {
            return new DuplicatesAccumulator(HashSet.empty(), HashSet.empty());
        }

        public DuplicatesAccumulator addSeenElement(String seen) {
            return this.withSeenElements(seenElements.add(seen));
        }

        public DuplicatesAccumulator addDuplicate(String duplicate) {
            return this.withDuplicates(duplicates.add(duplicate));
        }

        public boolean isElementSeen(String element) {
            return seenElements.contains(element);
        }
    }
}
