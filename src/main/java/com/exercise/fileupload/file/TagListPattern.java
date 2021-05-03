package com.exercise.fileupload.file;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TagListPatternValidator.class)
public @interface TagListPattern {

    String message() default "Tags cannot be empty or contain invalid characters: '+','-' or ' '";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
