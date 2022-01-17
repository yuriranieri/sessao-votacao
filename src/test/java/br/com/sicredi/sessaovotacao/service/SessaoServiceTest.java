package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.repository.SessaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarListSessaoEntity;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessaoServiceTest {

    @InjectMocks
    private SessaoService service;

    @Mock
    private SessaoRepository repository;

    @Test
    void quandoSalvarSessao_retornaSucesso() {
        SessaoEntity entity = criarSessaoEntity();

        when(repository.save(any(SessaoEntity.class)))
                .thenReturn(entity);

        assertNotNull(service.salvar(entity));
    }

    @Test
    void quandoListarSessao_retornaSucesso() {
        when(repository.findAll())
                .thenReturn(criarListSessaoEntity());

        assertThat(service.listar())
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void quandoBuscarSessaoPorId_retornaErroNotFoundException() {
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format("Sessao %d não encontrada", id));
    }

    @Test
    void quandoBuscarSessaoPorId_retornaSucesso() {
        Long id = anyLong();
        SessaoEntity entity = criarSessaoEntity();

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        assertThat(service.buscarPorId(id))
                .isNotNull()
                .isEqualTo(entity);
    }

}