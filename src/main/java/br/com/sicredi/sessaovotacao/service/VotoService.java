package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.model.VotoEntity;
import br.com.sicredi.sessaovotacao.model.VotoPK;
import br.com.sicredi.sessaovotacao.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class VotoService {

    private final VotoRepository repository;

    public VotoEntity salvar(VotoEntity entity) {
        log.info("salvar - {}", entity);
        return repository.save(entity);
    }

    public Page<VotoEntity> listar(Pageable pageable) {
        return repository.listarVotos(pageable);
    }

    public List<VotoEntity> listarPorIdSessao(Long idSessao) {
        log.info("buscar por idSessao - {}", idSessao);
        return repository.listarPorIdSessao(idSessao);
    }

    public Optional<VotoEntity> buscarPorId(VotoPK id) {
        log.info("buscar por id - {}", id);
        return repository.findById(id);
    }

    public List<VotoEntity> listarPorIdAssociado(Long idAssociado) {
        log.info("buscar por idAssociado - {}", idAssociado);
        return repository.listarPorIdAssociado(idAssociado);
    }
}
