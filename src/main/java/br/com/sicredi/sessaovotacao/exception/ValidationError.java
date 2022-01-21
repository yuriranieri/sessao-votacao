package br.com.sicredi.sessaovotacao.exception;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ValidationError extends StandardError {

    private final List<FieldMessage> errors;

    public ValidationError(int status, String message, List<FieldMessage> errors) {
        super(status, message);
        this.errors = errors;
    }
}
