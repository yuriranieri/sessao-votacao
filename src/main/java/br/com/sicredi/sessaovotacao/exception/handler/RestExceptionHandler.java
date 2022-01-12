package br.com.sicredi.sessaovotacao.exception.handler;

import br.com.sicredi.sessaovotacao.exception.*;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String ERRO_DE_VALIDACAO = "Erro de validação";

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        HttpStatus status = NOT_FOUND;
        StandardError error = new StandardError(status.value(), ex.getMessage());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ErrorBusinessException.class)
    public ResponseEntity<Object> handleErrorNegocioException(ErrorBusinessException ex) {
        HttpStatus status = BAD_REQUEST;
        StandardError error = new StandardError(status.value(), ex.getMessage());

        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                                                        HttpHeaders headers,
                                                        HttpStatus status,
                                                        WebRequest request) {
        String msg = String.format("Campo esperava receber o tipo '%s' mas recebeu o valor '%s' com o tipo '%s'",
                Objects.requireNonNull(ex.getRequiredType()).getSimpleName(),
                ex.getValue(),
                Objects.requireNonNull(ex.getValue()).getClass().getSimpleName());

        StandardError error = new StandardError(status.value(), msg);

        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<FieldMessage> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new FieldMessage(err.getDefaultMessage(), err.getField(), err.getRejectedValue()))
                .collect(Collectors.toList());

        ValidationError error = new ValidationError(status.value(), ERRO_DE_VALIDACAO, errors);

        return ResponseEntity.status(BAD_REQUEST).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Throwable rootCause = ex.getRootCause();
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, status);
        }

        StandardError errorResponse = new StandardError(status.value(), ex.getLocalizedMessage());
        return ResponseEntity.status(status).body(errorResponse);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpStatus status) {
        String fieldName = ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));

        String msg = String.format("O campo '%s' esperava receber o tipo '%s', " +
                        "mas recebeu o valor '%s' com o tipo '%s'",
                fieldName, ex.getTargetType().getSimpleName(), ex.getValue(), ex.getValue().getClass().getSimpleName());

        List<FieldMessage> errors = singletonList(new FieldMessage(msg, fieldName, ex.getValue()));
        ValidationError errorResponse = new ValidationError(status.value(), ERRO_DE_VALIDACAO, errors);

        return ResponseEntity.status(status).body(errorResponse);
    }

}