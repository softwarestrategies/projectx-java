package io.softwarestrategies.projectx.common.data.type;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Errors {

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class FieldError {
        String field;
        String code;
        String defaultMessage;

        public FieldError() {
        }

        public FieldError(String field, String code, String defaultMessage) {
            this.field = field;
            this.code = code;
            this.defaultMessage = defaultMessage;
        }
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class GlobalError {
        String code;
        String defaultMessage;

        public GlobalError() {
        }

        public GlobalError(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }

        public GlobalError(String code, String defaultMessage) {
            this.code = code;
            this.defaultMessage = defaultMessage;
        }
    }

    protected List<FieldError> fieldErrors = new ArrayList<>();
    protected List<GlobalError> globalErrors = new ArrayList<>();

    public Errors() {
    }

    public Errors(String code, String defaultMessage) {
        getGlobalErrors().add(new GlobalError(code, defaultMessage));
    }

}
