package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import br.com.sicredi.sessaovotacao.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PautaService {

    private final PautaRepository repository;

    public PautaEntity salvar(PautaEntity entity) {
        return repository.save(entity);
    }

    public List<PautaEntity> listar() {
        return repository.findAll();
    }

    public PautaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Pauta %d não encontrada", id)));
    }

    public PautaEntity atualizar(PautaEntity entity, Long id) {
        buscarPorId(id);
        entity.setId(id);
        return salvar(entity);
    }

    public void deletarPorId(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

}
