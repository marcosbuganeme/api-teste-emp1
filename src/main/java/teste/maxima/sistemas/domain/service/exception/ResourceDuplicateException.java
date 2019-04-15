package teste.maxima.sistemas.domain.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceDuplicateException extends RuntimeException {
    
    public ResourceDuplicateException(String message) {
        super(message);
    }
}