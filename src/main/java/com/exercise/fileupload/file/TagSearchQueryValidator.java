package com.exercise.fileupload.file;

import com.exercise.fileupload.common.ConstraintValidationMessageChanger;
import io.vavr.collection.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class TagSearchQueryValidator implements ConstraintValidator<TagSearchQueryConstraint, String> {

    private static final Set<String> validPrefixes = HashSet.of("+", "-");

    @Override
    public void initialize(TagSearchQueryConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String tagSearchQuery, ConstraintValidatorContext context) {
        Stream<String> tagsWithoutBlanks = splitQueryToTags(tagSearchQuery);
        List<String> invalidTags = findInvalidTags(tagsWithoutBlanks);

        if (!invalidTags.isEmpty()) {
            return new ConstraintValidationMessageChanger()
                    .failValidationWithCustomErrorMessage(context, "Following tags are invalid: ", invalidTags);
        }

        Set<String> duplicates = findDuplicates(tagsWithoutBlanks);
        if (!duplicates.isEmpty()) {
            return new ConstraintValidationMessageChanger()
                    .failValidationWithCustomErrorMessage(context, "Following tags are duplicated: ", duplicates);
        } else {
            return true;
        }
    }

    private Stream<String> splitQueryToTags(String tagSearchQuery) {
        return Stream.of(tagSearchQuery.split(" "))
                .filter(tag -> !tag.isEmpty());
    }

    private List<String> findInvalidTags(Stream<String> tagsWithoutBlanks) {
        TagInvalidCharsFinder invalidCharsFinder = new TagInvalidCharsFinder();

        return tagsWithoutBlanks
                .filter(tag -> isTagNotStartingWithValidChar(tag) || invalidCharsFinder.find(tag.substring(1)))
                .toList();
    }

    private boolean isTagNotStartingWithValidChar(String tag) {
        return !validPrefixes.contains(tag.substring(0, 1));
    }

    private Set<String> findDuplicates(Stream<String> tagsWithoutBlanks) {
        return new TagDuplicatesFinder()
                .findDuplicateTags(tagsWithoutBlanks);
    }

}
