package com.exercise.fileupload.common;

import io.vavr.collection.Traversable;

import javax.validation.ConstraintValidatorContext;

public class ConstraintValidationMessageChanger {

    public boolean failValidationWithCustomErrorMessage(
            ConstraintValidatorContext context,
            String baseErrorMessage,
            Traversable<String> invalidElements
    ) {
        String finalMessage = baseErrorMessage + invalidElements.mkString("'", ",", "'");
        return failValidationWithCustomErrorMessage(context, finalMessage);
    }

    public boolean failValidationWithCustomErrorMessage(
            ConstraintValidatorContext context,
            String errorMessage
    ) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();

        return false;
    }
}
