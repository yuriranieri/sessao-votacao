package br.com.sicredi.sessaovotacao.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AssociadoConverterTest {

    @InjectMocks
    private AssociadoConverter converter;

    @Test
    void quandoConverterRequestDtoToEntity() {
        assertEquals(criarAssociadoEntity().getCpf(), converter.requestDtoToEntity(criarAssociadoRequestDTO()).getCpf());
    }

    @Test
    void quandoConverterToResponseDto() {
        assertEquals(criarAssociadoResponseDTO(), converter.toResponseDto(criarAssociadoEntity()));
    }

    @Test
    void quandoConverterToListResponseDto() {
        assertEquals(criarListAssociadoResponseDTO(), converter.toListResponseDto(criarListAssociadoEntity()));
    }
}