package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SessaoService {

    private final SessaoRepository repository;

    public SessaoEntity salvar(SessaoEntity entity) {
        log.info("salvar - {}", entity);
        return repository.save(entity);
    }

    public Page<SessaoEntity> listar(Pageable pageable) {
        return repository.listarSessoes(pageable);
    }

    public SessaoEntity buscarPorId(Long id) {
        log.info("buscar por id - {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Sessao %d não encontrada", id)));
    }

    public Optional<SessaoEntity> buscarPorIdPauta(Long idPauta) {
        log.info("buscar por idPauta - {}", idPauta);
        return repository.findByPautaId(idPauta);
    }
}
