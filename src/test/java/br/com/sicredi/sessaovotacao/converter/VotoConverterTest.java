package br.com.sicredi.sessaovotacao.converter;

import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.exception.ErrorBusinessException;
import br.com.sicredi.sessaovotacao.model.VotoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoEntity;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoEntity;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoResponseDTO;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoConverterTest {

    @InjectMocks
    private VotoConverter converter;

    @Mock
    private AssociadoConverter associadoConverter;

    @Mock
    private SessaoConverter sessaoConverter;

    @Test
    void quandoConverterRequestDtoToEntity_retornaErro() {
        VotoRequestDTO requestDTO = criarVotoRequestDTO();
        requestDTO.setVoto('a');

        assertThatThrownBy(() -> converter.requestDtoToEntity(requestDTO))
                .isInstanceOf(ErrorBusinessException.class)
                .hasMessage("O associado só pode votar s para sim ou n para não");
    }

    @Test
    void quandoConverterRequestDtoToEntity_retornaSucesso() {
        VotoEntity entity = criarVotoEntity();
        VotoEntity entityConvertida = converter.requestDtoToEntity(criarVotoRequestDTO());

        assertAll(
                () -> assertEquals(entity.getId(), entityConvertida.getId()),
                () -> assertEquals(entity.getAssociado().getId(), entityConvertida.getAssociado().getId()),
                () -> assertEquals(entity.getSessao().getId(), entityConvertida.getSessao().getId()),
                () -> assertEquals(entity.getVoto(), entityConvertida.getVoto())
        );
    }

    @Test
    void quandoConverterToResponseDto_retornaSucesso() {
        when(associadoConverter.toResponseDto(criarAssociadoEntity()))
                .thenReturn(criarAssociadoResponseDTO());
        when(sessaoConverter.toResponseDto(criarSessaoEntity()))
                .thenReturn(criarSessaoResponseDTO());

        assertEquals(criarVotoResponseDTO(), converter.toResponseDto(criarVotoEntity()));
    }

    @Test
    void quandoConverterToListResponseDto_retornaSucesso() {
        when(associadoConverter.toResponseDto(criarAssociadoEntity()))
                .thenReturn(criarAssociadoResponseDTO());
        when(sessaoConverter.toResponseDto(criarSessaoEntity()))
                .thenReturn(criarSessaoResponseDTO());

        assertEquals(criarListVotoResponseDTO(), converter.toListResponseDto(criarListVotoEntity()));
    }

    @Test
    void quandoConverterToPageResponseDto_retornaSucesso() {
        when(associadoConverter.toResponseDto(criarAssociadoEntity()))
                .thenReturn(criarAssociadoResponseDTO());
        when(sessaoConverter.toResponseDto(criarSessaoEntity()))
                .thenReturn(criarSessaoResponseDTO());

        assertEquals(criarPageVotoResponseDTO(), converter.toPageResponseDto(criarPageVotoEntity()));
    }
}