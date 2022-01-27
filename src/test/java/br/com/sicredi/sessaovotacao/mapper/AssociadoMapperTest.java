package br.com.sicredi.sessaovotacao.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoEntity;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoRequestDTO;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarPageAssociadoEntity;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarPageAssociadoResponseDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AssociadoMapperTest {

    private final AssociadoMapper mapper = Mappers.getMapper(AssociadoMapper.class);

    @Test
    void quandoMapperRequestDtoToEntity_retornaSucesso() {
        assertEquals(criarAssociadoEntity().getCpf(), mapper.requestDtoToEntity(criarAssociadoRequestDTO()).getCpf());
    }

    @Test
    void quandoMapperRequestDtoToEntity_comNull_retornaSucesso() {
        assertNull(mapper.requestDtoToEntity(null));
    }

    @Test
    void quandoMapperToResponseDto_retornaSucesso() {
        assertEquals(criarAssociadoResponseDTO(), mapper.toResponseDto(criarAssociadoEntity()));
    }

    @Test
    void quandoMapperToResponseDto_comNull_retornaSucesso() {
        assertNull(mapper.toResponseDto(null));
    }

    @Test
    void quandoMapperToPageResponseDto_retornaSucesso() {
        assertEquals(criarPageAssociadoResponseDTO(), mapper.toPageResponseDto(criarPageAssociadoEntity()));
    }
}