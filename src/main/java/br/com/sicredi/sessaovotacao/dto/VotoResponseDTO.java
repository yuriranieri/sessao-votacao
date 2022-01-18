package br.com.sicredi.sessaovotacao.dto;

import br.com.sicredi.sessaovotacao.model.VotoPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class VotoResponseDTO {

    private VotoPK id;
    private String voto;
    private AssociadoResponseDTO associado;
    private SessaoResponseDTO sessao;
}
