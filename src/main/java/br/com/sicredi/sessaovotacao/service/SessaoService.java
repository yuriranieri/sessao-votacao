package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.repository.SessaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SessaoService {

    private final SessaoRepository repository;

    public SessaoEntity salvar(SessaoEntity entity) {
        return repository.save(entity);
    }

    public List<SessaoEntity> listar() {
        return repository.findAll();
    }

    public SessaoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Sessao %d não encontrada", id)));
    }

    public Optional<SessaoEntity> buscarPorIdPauta(Long idPauta) {
        return repository.findByPautaId(idPauta);
    }
}
