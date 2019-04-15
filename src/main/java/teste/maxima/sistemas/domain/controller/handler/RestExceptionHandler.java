package teste.maxima.sistemas.domain.controller.handler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import teste.maxima.sistemas.domain.controller.handler.builder.ErrorDetails;
import teste.maxima.sistemas.domain.controller.handler.builder.ValidationErrorDetails;
import teste.maxima.sistemas.domain.service.exception.ResourceDuplicateException;
import teste.maxima.sistemas.domain.service.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {

    	ErrorDetails details = ErrorDetails
                                    .Builder
                                    .newBuilder()
                                    .timestamp(new Date().getTime())
                                    .status(HttpStatus.NOT_FOUND.value())
                                    .title("Recurso não encontrado")
                                    .detail(exception.getMessage())
                                    .developerMessage(exception.getClass().getName())
                                    .build();

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceDuplicateException.class)
    public ResponseEntity<?> handleResourceDuplicateException(ResourceDuplicateException exception) {

    	ErrorDetails details = ErrorDetails
                                    .Builder
                                    .newBuilder()
                                    .timestamp(new Date().getTime())
                                    .status(HttpStatus.CONFLICT.value())
                                    .title("Recurso duplicado")
                                    .detail(exception.getMessage())
                                    .developerMessage(exception.getClass().getName())
                                    .build();

        return new ResponseEntity<>(details, HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {

        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();

        String fields = fieldErrors
                            .stream()
                            .map(FieldError::getField)
                            .collect(Collectors.joining(", "));

        String errorsField = fieldErrors
                                    .stream()
                                    .map(FieldError::getDefaultMessage)
                                    .collect(Collectors.joining(", "));

        ValidationErrorDetails rnfDetails = ValidationErrorDetails
                                                .Builder
                                                .newBuilder()
                                                .timestamp(new Date().getTime())
                                                .status(HttpStatus.BAD_REQUEST.value())
                                                .title("Erros de validação")
                                                .detail("Campos obrigatórios ou com regras mais especificas não foram atendidas")
                                                .developerMessage(manvException.getClass().getName())
                                                .field(fields)
                                                .errorFieldMessage(errorsField)
                                                .build();

        return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails
                                        .Builder
                                        .newBuilder()
                                        .timestamp(new Date().getTime())
                                        .status(status.value())
                                        .title("Erro interno do servidor")
                                        .detail(exception.getMessage())
                                        .developerMessage(exception.getClass().getName())
                                        .build();

        return new ResponseEntity<>(errorDetails, headers, status);
    }
}