package br.com.sicredi.sessaovotacao.repository;

import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {
    Optional<SessaoEntity> findByPautaId(Long idPauta);
}
