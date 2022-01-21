package br.com.sicredi.sessaovotacao.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class StandardError {

    private final int status;
    private final String message;

}
