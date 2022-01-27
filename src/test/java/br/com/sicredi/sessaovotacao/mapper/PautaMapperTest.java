package br.com.sicredi.sessaovotacao.mapper;

import br.com.sicredi.sessaovotacao.model.PautaEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPagePautaEntity;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPagePautaResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaEntity;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaRequestDTO;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaResponseDTO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PautaMapperTest {

    private final PautaMapper mapper = Mappers.getMapper(PautaMapper.class);

    @Test
    void quandoMapperRequestDtoToEntity_retornaSucesso() {
        PautaEntity entity = criarPautaEntity();
        PautaEntity entityConvertida = mapper.requestDtoToEntity(criarPautaRequestDTO());

        assertAll(
                () -> assertEquals(entity.getAssunto(), entityConvertida.getAssunto()),
                () -> assertEquals(entity.getDescricao(), entityConvertida.getDescricao())
        );
    }

    @Test
    void quandoMapperRequestDtoToEntity_comNull_retornaSucesso() {
        assertNull(mapper.requestDtoToEntity(null));
    }

    @Test
    void quandoMapperToResponseDto_retornaSucesso() {
        assertEquals(criarPautaResponseDTO(), mapper.toResponseDto(criarPautaEntity()));
    }

    @Test
    void quandoMapperToResponseDto_comNull_retornaSucesso() {
        assertNull(mapper.toResponseDto(null));
    }

    @Test
    void quandoMapperToPageResponseDto_retornaSucesso() {
        assertEquals(criarPagePautaResponseDTO(), mapper.toPageResponseDto(criarPagePautaEntity()));
    }
}