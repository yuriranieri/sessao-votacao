package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import br.com.sicredi.sessaovotacao.repository.PautaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPagePautaEntity;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaEntity;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.getPageable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PautaServiceTest {

    @InjectMocks
    private PautaService service;

    @Mock
    private PautaRepository repository;

    @Test
    void quandoSalvarPauta_retornaSucesso() {
        PautaEntity entity = criarPautaEntity();

        when(repository.save(any(PautaEntity.class)))
                .thenReturn(entity);

        assertNotNull(service.salvar(entity));
    }

    @Test
    void quandoListarPauta_retornaSucesso() {
        Pageable pageable = getPageable();

        when(repository.findAll(pageable))
                .thenReturn(criarPagePautaEntity());

        assertThat(service.listar(pageable))
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void quandoBuscarPautaPorId_retornaErroNotFoundException() {
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format("Pauta %d não encontrada", id));
    }

    @Test
    void quandoBuscarPautaPorId_retornaSucesso() {
        Long id = anyLong();
        PautaEntity entity = criarPautaEntity();

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        assertThat(service.buscarPorId(id))
                .isNotNull()
                .isEqualTo(entity);
    }

    @Test
    void quandoAtualizarPauta_retornaSucesso() {
        Long id = anyLong();
        PautaEntity entity = criarPautaEntity();

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        entity.setAssunto("11111111111");
        entity.setDescricao("11111111111");

        when(repository.save(any(PautaEntity.class)))
                .thenReturn(entity);

        assertEquals(entity, service.atualizar(entity, id));
    }

    @Test
    void quandoDeletarPautaPorId_retornaSucesso() {
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.of(criarPautaEntity()));

        service.deletarPorId(id);
        verify(repository, times(1)).deleteById(id);
    }
}