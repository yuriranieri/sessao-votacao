package br.com.sicredi.sessaovotacao.repository;

import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {

    @Query(value = "from SessaoEntity s join fetch s.pauta",
            countQuery = "select count(s) from SessaoEntity s ")
    Page<SessaoEntity> listarSessoes(Pageable pageable);

    Optional<SessaoEntity> findByPautaId(Long idPauta);
}
