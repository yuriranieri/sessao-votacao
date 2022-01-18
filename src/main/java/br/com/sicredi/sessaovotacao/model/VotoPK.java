package br.com.sicredi.sessaovotacao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class VotoPK implements Serializable {

    @Column(name = "idAssociado")
    private Long idAssociado;

    @Column(name = "idSessao")
    private Long idSessao;

}
