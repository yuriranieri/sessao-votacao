package br.com.sicredi.sessaovotacao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VotoRequestDTO {

    @JsonProperty(value = "id_associado")
    private Long idAssociado;

    @JsonProperty(value = "id_sessao")
    private Long idSessao;

    private Character voto;
}
