package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import br.com.sicredi.sessaovotacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssociadoService {

    private final AssociadoRepository repository;

    public AssociadoEntity salvar(AssociadoEntity entity) {
        log.info("salvar - {}", entity);
        return repository.save(entity);
    }

    public Page<AssociadoEntity> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public AssociadoEntity buscarPorId(Long id) {
        log.info("buscar por id - {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Associado %d não encontrado", id)));
    }

    public AssociadoEntity atualizar(AssociadoEntity entity, Long id) {
        log.info("atualizar - {}", entity);
        buscarPorId(id);
        entity.setId(id);
        return salvar(entity);
    }

    public void deletarPorId(Long id) {
        log.info("deletar por id - {}", id);
        buscarPorId(id);
        repository.deleteById(id);
    }
}
