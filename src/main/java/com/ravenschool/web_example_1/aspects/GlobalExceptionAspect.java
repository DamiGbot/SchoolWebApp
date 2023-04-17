package com.ravenschool.web_example_1.aspects;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
@Order(2)
public class GlobalExceptionAspect {

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception) {
        String errorMsg = null;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        if (exception.getMessage() != null) {
            errorMsg = exception.getMessage();
        } else if (exception.getCause() != null) {
            errorMsg = exception.getCause().toString();
        } else if (exception != null) {
            errorMsg = exception.toString();
        }
        modelAndView.addObject("errormsg", errorMsg);
        return modelAndView;
    }
}
