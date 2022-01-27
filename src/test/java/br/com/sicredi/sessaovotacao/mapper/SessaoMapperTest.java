package br.com.sicredi.sessaovotacao.mapper;

import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarPageSessaoEntity;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarPageSessaoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoEntity;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoRequestDTO;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoResponseDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SessaoMapperTest {

    private final SessaoMapper mapper = Mappers.getMapper(SessaoMapper.class);

    @Test
    void quandoMapperRequestDtoToEntity_retornaSucesso() {
        SessaoEntity entity = criarSessaoEntity();
        SessaoEntity entityConvertida = mapper.requestDtoToEntity(criarSessaoRequestDTO());

        assertEquals(entity.getPauta().getId(), entityConvertida.getPauta().getId());
    }

    @Test
    void quandoMapperRequestDtoToEntity_comNull_retornaSucesso() {
        assertNull(mapper.requestDtoToEntity(null));
    }

    @Test
    void quandoMapperToResponseDto_retornaSucesso() {
        assertEquals(criarSessaoResponseDTO(), mapper.toResponseDto(criarSessaoEntity()));
    }

    @Test
    void quandoMapperToResponseDto_comPautaNullretornaSucesso() {
        SessaoEntity entity = criarSessaoEntity();
        entity.setPauta(null);
        SessaoResponseDTO responseDTO = criarSessaoResponseDTO();
        responseDTO.setPautaResponseDTO(null);

        assertEquals(responseDTO, mapper.toResponseDto(entity));
    }

    @Test
    void quandoMapperToResponseDto_comNull_retornaSucesso() {
        assertNull(mapper.toResponseDto(null));
    }

    @Test
    void quandoMapperToPageResponseDto_retornaSucesso() {
        assertEquals(criarPageSessaoResponseDTO(), mapper.toPageResponseDto(criarPageSessaoEntity()));
    }
}