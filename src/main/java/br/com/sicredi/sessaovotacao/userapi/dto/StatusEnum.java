package br.com.sicredi.sessaovotacao.userapi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusEnum {

    UNABLE_TO_VOTE("UNABLE_TO_VOTE"),
    ABLE_TO_VOTE("ABLE_TO_VOTE");

    private final String descricao;
}
