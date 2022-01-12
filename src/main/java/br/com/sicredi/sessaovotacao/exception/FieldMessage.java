package br.com.sicredi.sessaovotacao.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FieldMessage {

    private final String message;
    private final String field;
    private final Object value;

}
