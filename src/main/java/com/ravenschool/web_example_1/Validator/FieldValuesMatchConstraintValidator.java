package com.ravenschool.web_example_1.Validator;

import com.ravenschool.web_example_1.Annotation.FieldsValueMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldValuesMatchConstraintValidator implements ConstraintValidator<FieldsValueMatch, Object> {
    private String fields;
    private String fieldsMatch;

    @Override
    public void initialize(FieldsValueMatch constraintAnnotation) {
        this.fields = constraintAnnotation.field();
        this.fieldsMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(final Object value, ConstraintValidatorContext constraintValidatorContext) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(fields);
        Object fieldMatch = new BeanWrapperImpl(value).getPropertyValue(fieldsMatch);

        boolean isValid = fieldValue != null && fieldValue.equals(fieldMatch);
        return isValid;
    }
}
