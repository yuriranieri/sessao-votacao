package br.com.sicredi.sessaovotacao.mapper;

import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.VotoResponseDTO;
import br.com.sicredi.sessaovotacao.exception.ErrorBusinessException;
import br.com.sicredi.sessaovotacao.model.VotoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mapstruct.factory.Mappers;

import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoEntityComPautaNull;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoResponseDtoComPautaNull;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarListVotoEntity;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarListVotoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarPageVotoEntity;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarPageVotoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoEntity;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoRequestDTO;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoResponseDTO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class VotoMapperTest {

    private final VotoMapper mapper = Mappers.getMapper(VotoMapper.class);

    @ParameterizedTest
    @CsvSource({"NAO, n", "SIM, s"})
    void quandoValidarVoto_retornaSucesso(String votoDescricao, Character voto) {
        assertEquals(votoDescricao, VotoMapper.validarVoto(voto));
    }

    @Test
    void quandoValidarVoto_retornaErro() {
        assertThatThrownBy(() -> VotoMapper.validarVoto('a'))
                .isInstanceOf(ErrorBusinessException.class)
                .hasMessage("O associado só pode votar s para sim ou n para não");
    }

    @Test
    void quandoRequestDtoToEntity_retornaSucesso() {
        VotoEntity entity = criarVotoEntity();
        VotoEntity entityConvertida = mapper.requestDtoToEntity(criarVotoRequestDTO());

        assertAll(
                () -> assertEquals(entity.getId(), entityConvertida.getId()),
                () -> assertEquals(entity.getAssociado().getId(), entityConvertida.getAssociado().getId()),
                () -> assertEquals(entity.getSessao().getId(), entityConvertida.getSessao().getId()),
                () -> assertEquals(entity.getVoto(), entityConvertida.getVoto())
        );
    }

    @Test
    void quandoRequestDtoToEntity_comvotoPkDtoNull_retornaSucesso() {
        VotoEntity entity = new VotoEntity(null, null, null, "SIM");
        VotoRequestDTO requestDTO = criarVotoRequestDTO();
        requestDTO.setId(null);
        VotoEntity entityConvertida = mapper.requestDtoToEntity(requestDTO);

        assertAll(
                () -> assertEquals(entity.getId(), entityConvertida.getId()),
                () -> assertEquals(entity.getAssociado(), entityConvertida.getAssociado()),
                () -> assertEquals(entity.getSessao(), entityConvertida.getSessao()),
                () -> assertEquals(entity.getVoto(), entityConvertida.getVoto())
        );
    }

    @Test
    void quandoRequestDtoToEntity_comNull_retornaSucesso() {
        assertNull(mapper.requestDtoToEntity(null));
    }

    @Test
    void quandoToResponseDto_retornaSucesso() {
        assertEquals(criarVotoResponseDTO(), mapper.toResponseDto(criarVotoEntity()));
    }

    @Test
    void quandoToResponseDto_comSessaoNull_retornaSucesso() {
        VotoEntity entity = criarVotoEntity();
        entity.setId(null);
        entity.setSessao(null);
        entity.setAssociado(null);

        VotoResponseDTO responseDTO = criarVotoResponseDTO();
        responseDTO.setId(null);
        responseDTO.setSessao(null);
        responseDTO.setAssociado(null);

        assertEquals(responseDTO, mapper.toResponseDto(entity));
    }

    @Test
    void quandoToResponseDto_comPautaNull_retornaSucesso() {
        VotoEntity entity = criarVotoEntity();
        VotoResponseDTO responseDTO = criarVotoResponseDTO();
        entity.setSessao(criarSessaoEntityComPautaNull());
        responseDTO.setSessao(criarSessaoResponseDtoComPautaNull());

        assertEquals(responseDTO, mapper.toResponseDto(entity));
    }

    @Test
    void quandoToResponseDto_comNull_retornaSucesso() {
        assertNull(mapper.toResponseDto(null));
    }

    @Test
    void quandoToListResponseDto_retornaSucesso() {
        assertEquals(criarListVotoResponseDTO(), mapper.toListResponseDto(criarListVotoEntity()));
    }

    @Test
    void quandoToListResponseDto_comNull_retornaSucesso() {
        assertNull(mapper.toListResponseDto(null));
    }

    @Test
    void quandoToPageResponseDto_retornaSucesso() {
        assertEquals(criarPageVotoResponseDTO(), mapper.toPageResponseDto(criarPageVotoEntity()));
    }

}