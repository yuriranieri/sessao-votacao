package br.com.sicredi.sessaovotacao.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationError extends StandardError {

    private List<FieldMessage> errors;

    public ValidationError(int status, String message, List<FieldMessage> errors) {
        super(status, message);
        this.errors = errors;
    }
}
