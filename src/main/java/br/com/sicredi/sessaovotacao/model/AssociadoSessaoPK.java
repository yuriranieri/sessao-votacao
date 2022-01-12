package br.com.sicredi.sessaovotacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@Data
@Embeddable
public class AssociadoSessaoPK implements Serializable {

    @Column(name = "idAssociado")
    private Long idAssociado;

    @Column(name = "idSessao")
    private Long idSessao;

}
