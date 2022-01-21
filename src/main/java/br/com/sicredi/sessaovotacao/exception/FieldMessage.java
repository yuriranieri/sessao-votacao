package br.com.sicredi.sessaovotacao.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class FieldMessage {

    private final String message;
    private final String field;
    private final Object value;

}
