package br.com.sicredi.sessaovotacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
