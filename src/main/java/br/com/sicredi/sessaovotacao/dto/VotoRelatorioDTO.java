package br.com.sicredi.sessaovotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VotoRelatorioDTO {

    @JsonProperty(value = "quantidade_votos_sim")
    private long quantidadeVotosSim;

    @JsonProperty(value = "quantidade_votos_nao")
    private long quantidadeVotosNao;
}
