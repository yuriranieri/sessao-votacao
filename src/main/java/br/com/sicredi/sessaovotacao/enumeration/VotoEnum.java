package br.com.sicredi.sessaovotacao.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum VotoEnum {

    SIM('S'),
    NAO('N');

    private final Character voto;

    public static Optional<VotoEnum> findByName(Character name) {
        return Stream.of(values())
                .filter(voto -> Objects.equals(voto.getVoto(), Character.toUpperCase(name)))
                .findFirst();
    }

}
