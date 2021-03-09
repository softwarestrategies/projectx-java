package io.softwarestrategies.projectx.common.exceptions;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException {

    private final transient BindingResult bindingResult;

    public ValidationException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }

    public BindingResult getBindingResult() {
        return this.bindingResult;
    }
}
