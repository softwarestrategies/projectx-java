package io.softwarestrategies.projectx.resource.web;

import io.softwarestrategies.projectx.common.data.type.ApiResponse;
import io.softwarestrategies.projectx.common.data.type.Errors;
import io.softwarestrategies.projectx.common.exceptions.EntityNotFoundException;
import io.softwarestrategies.projectx.common.exceptions.ValidationException;
import io.softwarestrategies.projectx.common.utils.ErrorUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private final static Log log = LogFactory.getLog(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ApiResponse> unknownException(EntityNotFoundException ex) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ApiResponse(404, new Errors("404", ex.getMessage())),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ApiResponse> validationException(ValidationException ex) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ApiResponse(500, ErrorUtils.getErrorsFromBindingResult(ex.getBindingResult())),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ApiResponse> unknownException(Exception ex) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(
                new ApiResponse(500, new Errors("500", ex.getMessage())),
                HttpStatus.BAD_REQUEST
        );
    }
}