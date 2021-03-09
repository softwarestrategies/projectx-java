package io.softwarestrategies.projectx.common.utils;

import io.softwarestrategies.projectx.common.data.type.Errors;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorUtils {

    public static void populateBindingResult(Errors errors, BindingResult bindingResult) {
        for (Errors.GlobalError globalError : errors.getGlobalErrors()) {
            bindingResult.reject(globalError.getCode(), globalError.getDefaultMessage());
        }

        for (Errors.FieldError fieldError : errors.getFieldErrors()) {
            bindingResult.rejectValue(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage());
        }
    }

    public static Errors getErrorsFromBindingResult(BindingResult bindingResult) {
        Errors errors = new Errors();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.getFieldErrors().add(
                    new Errors.FieldError(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage())
            );
        }

        for (ObjectError globalError : bindingResult.getGlobalErrors()) {
            errors.getGlobalErrors().add(
                    new Errors.GlobalError(globalError.getCode(), globalError.getDefaultMessage())
            );
        }

        return errors;
    }
}
