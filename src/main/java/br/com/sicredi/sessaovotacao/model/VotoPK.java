package br.com.sicredi.sessaovotacao.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class VotoPK implements Serializable {

    @Column(name = "id_associado")
    private Long idAssociado;

    @Column(name = "id_sessao")
    private Long idSessao;

}
