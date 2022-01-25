package br.com.sicredi.sessaovotacao.utils;

import br.com.sicredi.sessaovotacao.dto.PautaRequestDTO;
import br.com.sicredi.sessaovotacao.dto.PautaResponseDTO;
import br.com.sicredi.sessaovotacao.model.PautaEntity;

import java.util.List;

import static java.util.Collections.singletonList;

public abstract class PautaUtils {

    public static PautaRequestDTO criarPautaRequestDTO() {
        return PautaRequestDTO.builder()
                .assunto("teste")
                .descricao("descricao teste")
                .build();
    }

    public static PautaResponseDTO criarPautaResponseDTO() {
        return PautaResponseDTO.builder()
                .id(1L)
                .assunto("teste")
                .descricao("descricao teste")
                .build();
    }

    public static PautaEntity criarPautaEntity() {
        return PautaEntity.builder()
                .id(1L)
                .assunto("teste")
                .descricao("descricao teste")
                .build();
    }

    public static PautaEntity criarPautaEntityNoArgs() {
        PautaEntity entity = new PautaEntity();
        entity.setId(1L);
        entity.setAssunto("teste");
        entity.setDescricao("descricao teste");
        return entity;
    }

    public static List<PautaResponseDTO> criarListPautaResponseDTO() {
        return singletonList(criarPautaResponseDTO());
    }

    public static List<PautaEntity> criarListPautaEntity() {
        return singletonList(criarPautaEntity());
    }

}
