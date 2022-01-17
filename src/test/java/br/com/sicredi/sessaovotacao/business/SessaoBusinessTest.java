package br.com.sicredi.sessaovotacao.business;

import br.com.sicredi.sessaovotacao.converter.SessaoConverter;
import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.exception.ErrorBusinessException;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.service.PautaService;
import br.com.sicredi.sessaovotacao.service.SessaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaEntity;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
    void quandoSalvarSessao_comIdPautaExistente_retornaErro() {
        Long idPauta = 1L;
        SessaoEntity entity = criarSessaoEntity();

        when(converter.requestDtoToEntity(any(SessaoRequestDTO.class)))
                .thenReturn(entity);
        when(service.buscarPorIdPauta(idPauta))
                .thenReturn(Optional.of(criarSessaoEntity()));

        SessaoRequestDTO requestDTO = criarSessaoRequestDTO();
        assertThatThrownBy(() -> business.salvar(requestDTO))
                .isInstanceOf(ErrorBusinessException.class)
                .hasMessage(String.format("Já existe sessão com a pauta %d", idPauta));
    }

    @Test
    void quandoSalvarSessao_retornaSucesso() {
        SessaoEntity entity = criarSessaoEntity();
        SessaoResponseDTO responseDTO = criarSessaoResponseDTO();

        when(converter.requestDtoToEntity(any(SessaoRequestDTO.class)))
                .thenReturn(entity);
        when(service.buscarPorIdPauta(anyLong()))
                .thenReturn(Optional.empty());
        when(pautaService.buscarPorId(anyLong()))
                .thenReturn(criarPautaEntity());
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

    @Test
    void quandoBuscarSessaoPorIdPauta_retornaErro() {
        Long id = anyLong();
        SessaoResponseDTO responseDTO = criarSessaoResponseDTO();

        when(service.buscarPorIdPauta(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> business.buscarPorIdPauta(id))
                .isInstanceOf(ErrorBusinessException.class)
                .hasMessage(String.format("Não existe sessão para a pauta %d", id));
    }

    @Test
    void quandoBuscarSessaoPorIdPauta_retornaSucesso() {
        Long id = anyLong();
        SessaoResponseDTO responseDTO = criarSessaoResponseDTO();

        when(service.buscarPorIdPauta(id))
                .thenReturn(Optional.of(criarSessaoEntity()));
        when(converter.toResponseDto(any(SessaoEntity.class)))
                .thenReturn(responseDTO);

        assertThat(business.buscarPorIdPauta(id))
                .isNotNull()
                .isEqualTo(responseDTO);
    }

}