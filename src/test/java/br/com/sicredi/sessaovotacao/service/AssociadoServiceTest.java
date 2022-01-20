package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import br.com.sicredi.sessaovotacao.repository.AssociadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoEntity;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarListAssociadoEntity;
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
class AssociadoServiceTest {

    @InjectMocks
    private AssociadoService service;

    @Mock
    private AssociadoRepository repository;

    @Test
    void quandoSalvarAssociado_retornaSucesso() {
        AssociadoEntity entity = criarAssociadoEntity();

        when(repository.save(any(AssociadoEntity.class)))
                .thenReturn(entity);

        assertNotNull(service.salvar(entity));
    }

    @Test
    void quandoListarAssociado_retornaSucesso() {
        when(repository.findAll())
                .thenReturn(criarListAssociadoEntity());
        assertThat(service.listar())
                .isNotNull()
                .hasSize(1);
    }

    @Test
    void quandoBuscarAssociadoPorId_retornaErroNotFoundException() {
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.buscarPorId(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(String.format("Associado %d não encontrado", id));
    }

    @Test
    void quandoBuscarAssociadoPorId_retornaSucesso() {
        Long id = anyLong();
        AssociadoEntity entity = criarAssociadoEntity();

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        assertThat(service.buscarPorId(id))
                .isNotNull()
                .isEqualTo(entity);
    }

    @Test
    void quandoAtualizarAssociado_retornaSucesso() {
        Long id = anyLong();
        AssociadoEntity entity = criarAssociadoEntity();

        when(repository.findById(id))
                .thenReturn(Optional.of(entity));

        entity.setCpf("11111111111");

        when(repository.save(any(AssociadoEntity.class)))
                .thenReturn(entity);

        assertEquals(entity, service.atualizar(entity, id));
    }

    @Test
    void quandoDeletarAssociadoPorId_retornaSucesso() {
        Long id = anyLong();

        when(repository.findById(id))
                .thenReturn(Optional.of(criarAssociadoEntity()));

        service.deletarPorId(id);
        verify(repository, times(1)).deleteById(id);
    }
}