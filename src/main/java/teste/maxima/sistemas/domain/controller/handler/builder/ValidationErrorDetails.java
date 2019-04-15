package teste.maxima.sistemas.domain.controller.handler.builder;

public class ValidationErrorDetails extends ErrorDetails {

    private String field;
    private String errorFieldMessage;

    public static final class Builder {

        private String title;
        private int status;
        private String detail;
        private long timestamp;
        private String developerMessage;
        private String field;
        private String errorFieldMessage;

        private Builder() {}

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder errorFieldMessage(String errorFieldMessage) {
            this.errorFieldMessage = errorFieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {

            ValidationErrorDetails details = new ValidationErrorDetails();
            details.setDeveloperMessage(developerMessage);
            details.setTitle(title);
            details.setDetail(detail);
            details.setTimestamp(timestamp);
            details.setStatus(status);
            details.errorFieldMessage = errorFieldMessage;
            details.field = field;

            return details;
        }
    }

    public String getField() {
        return field;
    }

    public String getErrorFieldMessage() {
        return errorFieldMessage;
    }
}