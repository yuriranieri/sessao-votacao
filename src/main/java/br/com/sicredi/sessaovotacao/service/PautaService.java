package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import br.com.sicredi.sessaovotacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PautaService {

    private final PautaRepository repository;

    public PautaEntity salvar(PautaEntity entity) {
        log.info("salvar - {}", entity);
        return repository.save(entity);
    }

    public Page<PautaEntity> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public PautaEntity buscarPorId(Long id) {
        log.info("buscar por id - {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Pauta %d não encontrada", id)));
    }

    public PautaEntity atualizar(PautaEntity entity, Long id) {
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
