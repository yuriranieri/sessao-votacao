package br.com.sicredi.sessaovotacao.repository;

import br.com.sicredi.sessaovotacao.model.VotoEntity;
import br.com.sicredi.sessaovotacao.model.VotoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, VotoPK> {

    @Query("from VotoEntity v " +
            "join fetch v.associado " +
            "join fetch v.sessao s " +
            "join fetch s.pauta")
    List<VotoEntity> listarVotos();

    @Query("from VotoEntity v " +
            "join fetch v.associado " +
            "join fetch v.sessao s " +
            "join fetch s.pauta " +
            "where v.sessao.id = :idSessao")
    List<VotoEntity> listarPorIdSessao(Long idSessao);

}
