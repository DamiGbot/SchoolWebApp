package com.ravenschool.web_example_1.Annotation;

import com.ravenschool.web_example_1.Validator.FieldValuesMatchConstraintValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FieldValuesMatchConstraintValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsValueMatch {
    String message() default "Fields do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();
    String fieldMatch();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
