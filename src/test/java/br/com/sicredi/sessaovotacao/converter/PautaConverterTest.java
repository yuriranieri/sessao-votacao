package br.com.sicredi.sessaovotacao.converter;

import br.com.sicredi.sessaovotacao.model.PautaEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPagePautaEntity;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPagePautaResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaEntity;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaRequestDTO;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaResponseDTO;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PautaConverterTest {

    @InjectMocks
    private PautaConverter converter;

    @Test
    void quandoConverterRequestDtoToEntity_retornaSucesso() {
        PautaEntity entity = criarPautaEntity();
        PautaEntity entityConvertida = converter.requestDtoToEntity(criarPautaRequestDTO());

        assertAll(
                () -> assertEquals(entity.getAssunto(), entityConvertida.getAssunto()),
                () -> assertEquals(entity.getDescricao(), entityConvertida.getDescricao())
        );
    }

    @Test
    void quandoConverterToResponseDto_retornaSucesso() {
        assertEquals(criarPautaResponseDTO(), converter.toResponseDto(criarPautaEntity()));
    }

    @Test
    void quandoConverterToPageResponseDto_retornaSucesso() {
        assertEquals(criarPagePautaResponseDTO(), converter.toPageResponseDto(criarPagePautaEntity()));
    }
}