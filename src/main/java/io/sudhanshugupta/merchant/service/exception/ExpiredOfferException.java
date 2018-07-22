package io.sudhanshugupta.merchant.service.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
public class ExpiredOfferException extends RuntimeException {

    private static final long serialVersionUID = 6896091240588240505L;
    private final Map<String, String> fields;

    @Builder
    public ExpiredOfferException(String message, Throwable cause,
            Map<String, String> fields) {
        super(message, cause);
        this.fields = fields;
    }

    public static class ExpiredOfferExceptionBuilder {

        private Map<String, String> fields;

        public ExpiredOfferExceptionBuilder() {
            this.fields = new HashMap<>();
        }

        public <T> ExpiredOfferExceptionBuilder field(String fieldName, T fieldValue) {
            this.fields.put(fieldName, String.valueOf(fieldValue));
            return this;
        }
    }
}
