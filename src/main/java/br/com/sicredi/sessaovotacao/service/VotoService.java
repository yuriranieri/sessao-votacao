package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.model.VotoEntity;
import br.com.sicredi.sessaovotacao.model.VotoPK;
import br.com.sicredi.sessaovotacao.repository.VotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VotoService {

    private final VotoRepository repository;

    public VotoEntity salvar(VotoEntity entity) {
        return repository.save(entity);
    }

    public List<VotoEntity> listar() {
        return repository.listarVotos();
    }

    public List<VotoEntity> listarPorIdSessao(Long idSessao) {
        return repository.listarPorIdSessao(idSessao);
    }

    public Optional<VotoEntity> buscarPorId(VotoPK id) {
        return repository.findById(id);
    }
}
