package br.com.sicredi.sessaovotacao.dto;

import br.com.sicredi.sessaovotacao.model.VotoPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class VotoResponseDTO {

    private VotoPK id;
    private String voto;
    private AssociadoResponseDTO associado;
    private SessaoResponseDTO sessao;
}
