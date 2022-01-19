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

import static br.com.sicredi.sessaovotacao.utils.VotoUtils.*;
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
        VotoEntity entity = criarVotoEntity();

        when(repository.save(any(VotoEntity.class)))
                .thenReturn(entity);

        assertNotNull(service.salvar(entity));
    }

    @Test
    void quandoListarVoto_retornaSucesso() {
        when(repository.listarVotos())
                .thenReturn(criarListVotoEntity());

        assertThat(service.listar())
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
}