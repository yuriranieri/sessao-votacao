package br.com.sicredi.sessaovotacao.business;

import br.com.sicredi.sessaovotacao.converter.SessaoConverter;
import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.service.PautaService;
import br.com.sicredi.sessaovotacao.service.SessaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoBusinessTest {

    @InjectMocks
    private SessaoBusiness business;

    @Mock
    private SessaoConverter converter;

    @Mock
    private SessaoService service;

    @Mock
    private PautaService pautaService;

    @Test
    void quandoSalvarSessao_retornaSucesso() {
        SessaoEntity entity = criarSessaoEntity();
        SessaoResponseDTO responseDTO = criarSessaoResponseDTO();

        when(converter.requestDtoToEntity(any(SessaoRequestDTO.class)))
                .thenReturn(entity);
        when(service.salvar(any(SessaoEntity.class)))
                .thenReturn(entity);
        when(converter.toResponseDto(any(SessaoEntity.class)))
                .thenReturn(responseDTO);

        assertThat(business.salvar(criarSessaoRequestDTO()))
                .isNotNull()
                .isEqualTo(responseDTO);
    }

    @Test
    void quandoListarSessao_retornaSucesso() {
        when(service.listar())
                .thenReturn(criarListSessaoEntity());
        when(converter.toListResponseDto(anyList()))
                .thenReturn(criarListSessaoResponseDTO());

        assertThat(business.listar())
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void quandoBuscarSessaoPorId_retornaSucesso() {
        Long id = anyLong();
        SessaoResponseDTO responseDTO = criarSessaoResponseDTO();

        when(service.buscarPorId(id))
                .thenReturn(criarSessaoEntity());
        when(converter.toResponseDto(any(SessaoEntity.class)))
                .thenReturn(responseDTO);

        assertThat(business.buscarPorId(id))
                .isNotNull()
                .isEqualTo(responseDTO);
    }

}