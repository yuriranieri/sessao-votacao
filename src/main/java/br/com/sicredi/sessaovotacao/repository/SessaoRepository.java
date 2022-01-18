package br.com.sicredi.sessaovotacao.repository;

import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {

    @Query("from SessaoEntity s join fetch s.pauta")
    List<SessaoEntity> listarSessoes();

    Optional<SessaoEntity> findByPautaId(Long idPauta);
}
