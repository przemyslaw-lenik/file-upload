package com.exercise.fileupload.file;

import com.exercise.fileupload.common.ConstraintValidationMessageChanger;
import io.vavr.collection.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class TagListPatternValidator implements ConstraintValidator<TagListPattern, List<String>> {

    @Override
    public void initialize(TagListPattern constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<String> tags, ConstraintValidatorContext context) {
        if (tags == null) {
            return new ConstraintValidationMessageChanger()
                    .failValidationWithCustomErrorMessage(context, "Tags cannot be null");
        }

        List<String> invalidTags = isTagsBlankOrContainingInvalidChars(tags);
        if (invalidTags.isEmpty()) {
            return true;
        } else {
            return new ConstraintValidationMessageChanger().failValidationWithCustomErrorMessage(
                    context,
                    "Validation failed - some tags are invalid: ",
                    invalidTags
            );
        }
    }

    private List<String> isTagsBlankOrContainingInvalidChars(List<String> tags) {
        TagInvalidCharsFinder invalidCharsFinder = new TagInvalidCharsFinder();

        return tags.filter(tag -> tag == null || tag.isEmpty() || invalidCharsFinder.find(tag));
    }
}
