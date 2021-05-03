package com.exercise.fileupload.file;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TagSearchQueryValidator.class)
public @interface TagSearchQueryConstraint {

    String message() default "Tags must be separated with whitespaces and start with '+' or '-'";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
