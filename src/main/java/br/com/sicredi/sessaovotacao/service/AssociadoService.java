package br.com.sicredi.sessaovotacao.service;

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
}
