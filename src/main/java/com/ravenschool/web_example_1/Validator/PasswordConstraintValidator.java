package com.ravenschool.web_example_1.Validator;

import com.ravenschool.web_example_1.Annotation.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValidator, String> {

    List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = Arrays.asList("12345", "password", "qwerty");
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext constraintValidatorContext) {

        return !passwordField.isEmpty() && !weakPasswords.contains(passwordField);
    }
}
