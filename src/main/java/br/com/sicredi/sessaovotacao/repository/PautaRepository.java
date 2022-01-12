package br.com.sicredi.sessaovotacao.repository;

import br.com.sicredi.sessaovotacao.model.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Long> {
}
