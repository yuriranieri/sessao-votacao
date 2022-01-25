package br.com.sicredi.sessaovotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class SessaoRequestDTO {

    @NotNull(message = "tempo_expiracao_em_minutos é um campo obrigatório")
    @JsonProperty(value = "tempo_expiracao_em_minutos")
    private Integer tempoExpiracaoEmMinutos;

    @NotNull(message = "id_pauta é um campo obrigatório")
    @JsonProperty(value = "id_pauta")
    private Long idPauta;

}
