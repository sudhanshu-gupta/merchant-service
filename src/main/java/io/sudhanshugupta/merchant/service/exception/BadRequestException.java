package io.sudhanshugupta.merchant.service.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 6254861457724698697L;
    private final Map<String, String> fields;

    @Builder
    private BadRequestException(String message, Throwable cause,
            Map<String, String> fields) {
        super(message, cause);
        this.fields = fields;
    }

    public static class BadRequestExceptionBuilder {

        private Map<String, String> fields;

        public BadRequestExceptionBuilder() {
            this.fields = new HashMap<>();
        }

        public <T> BadRequestExceptionBuilder field(String fieldName, T fieldValue) {
            this.fields.put(fieldName, String.valueOf(fieldValue));
            return this;
        }
    }
}
