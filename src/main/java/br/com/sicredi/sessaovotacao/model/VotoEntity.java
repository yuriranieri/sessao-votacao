package br.com.sicredi.sessaovotacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Voto")
public class VotoEntity {

    @EmbeddedId
    private VotoPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAssociado")
    @JoinColumn(name = "id_associado")
    private AssociadoEntity associado;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSessao")
    @JoinColumn(name = "id_sessao")
    private SessaoEntity sessao;

    private String voto;

}
