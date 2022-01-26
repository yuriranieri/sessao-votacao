package br.com.sicredi.sessaovotacao.utils;

import br.com.sicredi.sessaovotacao.dto.PautaRequestDTO;
import br.com.sicredi.sessaovotacao.dto.PautaResponseDTO;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static br.com.sicredi.sessaovotacao.utils.VotoUtils.getPageable;
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

    public static Page<PautaResponseDTO> criarPagePautaResponseDTO() {
        return new PageImpl<>(criarListPautaResponseDTO(), getPageable(), 1L);
    }

    public static Page<PautaEntity> criarPagePautaEntity() {
        return new PageImpl<>(criarListPautaEntity(), getPageable(), 1L);
    }

}
