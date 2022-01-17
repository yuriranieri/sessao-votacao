package br.com.sicredi.sessaovotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SessaoRequestDTO {

    @NotNull(message = "tempo_expiracao_em_minutos é um campo obrigatório")
    @JsonProperty(value = "tempo_expiracao_em_minutos")
    private int tempoExpiracaoEmMinutos;

    @NotNull(message = "id_pauta é um campo obrigatório")
    @JsonProperty(value = "id_pauta")
    private Long idPauta;

}
