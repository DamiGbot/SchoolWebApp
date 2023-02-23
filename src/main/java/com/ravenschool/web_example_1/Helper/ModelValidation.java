package com.ravenschool.web_example_1.Helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Slf4j
@Component
public class ModelValidation<T> {

    public String modelErrorPage(Errors errors, String errorPage, String errorMsg) {
        log.error(errorMsg + " validation failed due to: " + errors);
        return errorPage;
    }
}
