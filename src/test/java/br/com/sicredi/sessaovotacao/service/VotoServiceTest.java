package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.model.VotoEntity;
import br.com.sicredi.sessaovotacao.model.VotoPK;
import br.com.sicredi.sessaovotacao.repository.VotoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarListVotoEntity;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarPageVotoEntity;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoEntity;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoEntityNoArgs;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoPK;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.getPageable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VotoServiceTest {

    @InjectMocks
    private VotoService service;

    @Mock
    private VotoRepository repository;

    @Test
    void quandoSalvarVoto_retornaSucesso() {
        VotoEntity entity = criarVotoEntityNoArgs();

        when(repository.save(any(VotoEntity.class)))
                .thenReturn(entity);

        assertNotNull(service.salvar(entity));
    }

    @Test
    void quandoListarVoto_retornaSucesso() {
        when(repository.listarVotos(any()))
                .thenReturn(criarPageVotoEntity());

        assertThat(service.listar(getPageable()))
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void quandoListarVotoPorIdSessao_retornaSucesso() {
        Long idSessao = anyLong();

        when(repository.listarPorIdSessao(idSessao))
                .thenReturn(criarListVotoEntity());

        assertThat(service.listarPorIdSessao(idSessao))
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void quandoBuscarVotoPorId_retornaSucesso() {
        Optional<VotoEntity> entity = Optional.of(criarVotoEntity());

        when(repository.findById(any(VotoPK.class)))
                .thenReturn(entity);

        assertThat(service.buscarPorId(criarVotoPK()))
                .isNotNull()
                .isEqualTo(entity);
    }

    @Test
    void quandoListarVotoPorIdAssociado_retornaSucesso() {
        Long idSessao = anyLong();

        when(repository.listarPorIdAssociado(idSessao, any()))
                .thenReturn(criarPageVotoEntity());

        assertThat(service.listarPorIdAssociado(idSessao, getPageable()))
                .isNotNull()
                .hasSize(1);
    }
}