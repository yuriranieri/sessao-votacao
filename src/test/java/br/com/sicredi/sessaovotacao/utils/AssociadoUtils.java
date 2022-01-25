package br.com.sicredi.sessaovotacao.utils;

import br.com.sicredi.sessaovotacao.dto.AssociadoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.AssociadoResponseDTO;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;

import java.util.List;

import static java.util.Collections.singletonList;

public abstract class AssociadoUtils {

    public static AssociadoRequestDTO criarAssociadoRequestDTO() {
        return AssociadoRequestDTO.builder()
                .cpf("11122233344")
                .build();
    }

    public static AssociadoResponseDTO criarAssociadoResponseDTO() {
        return AssociadoResponseDTO.builder()
                .id(1L)
                .cpf("11122233344")
                .build();
    }

    public static AssociadoEntity criarAssociadoEntity() {
        return AssociadoEntity.builder()
                .id(1L)
                .cpf("11122233344")
                .build();
    }

    public static AssociadoEntity criarAssociadoEntityNoArgs() {
        AssociadoEntity entity = new AssociadoEntity();
        entity.setId(1L);
        entity.setCpf("22095519000");
        return entity;
    }

    public static List<AssociadoEntity> criarListAssociadoEntity() {
        return singletonList(criarAssociadoEntity());
    }

    public static List<AssociadoResponseDTO> criarListAssociadoResponseDTO() {
        return singletonList(criarAssociadoResponseDTO());
    }

}
