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
public class VotoRequestDTO {

    @NotNull(message = "id_associado é um campo obrigatório")
    @JsonProperty(value = "id_associado")
    private Long idAssociado;

    @NotNull(message = "id_sessao é um campo obrigatório")
    @JsonProperty(value = "id_sessao")
    private Long idSessao;

    @NotNull(message = "voto é um campo obrigatório")
    private Character voto;
}
