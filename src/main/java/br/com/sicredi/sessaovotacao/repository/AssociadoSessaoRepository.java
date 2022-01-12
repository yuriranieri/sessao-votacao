package br.com.sicredi.sessaovotacao.repository;

import br.com.sicredi.sessaovotacao.model.AssociadoSessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoSessaoRepository extends JpaRepository<AssociadoSessaoEntity, Long> {
}
