package br.com.sicredi.sessaovotacao.service;

import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import br.com.sicredi.sessaovotacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssociadoService {

    private final AssociadoRepository repository;

    public AssociadoEntity salvar(AssociadoEntity entity) {
        return repository.save(entity);
    }

    public List<AssociadoEntity> listar() {
        return repository.findAll();
    }

    public AssociadoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Associado %d não encontrado", id)));
    }

    public AssociadoEntity atualizar(AssociadoEntity entity, Long id) {
        buscarPorId(id);
        entity.setId(id);
        return salvar(entity);
    }

    public void deletarPorId(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
