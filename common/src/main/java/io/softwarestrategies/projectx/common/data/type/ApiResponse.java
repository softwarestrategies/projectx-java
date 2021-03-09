package io.softwarestrategies.projectx.common.data.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResponse implements Serializable {

    private int errorCode = 0;
    private transient Object data;
    private Errors errors;

    public ApiResponse() {
    }

    public ApiResponse(int errorCode, Errors errors) {
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public ApiResponse(Object data) {
        this.data = data;
    }
}
