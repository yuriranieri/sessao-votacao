package br.com.sicredi.sessaovotacao.repository;

import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<AssociadoEntity, Long> {
}
