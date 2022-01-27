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
public class VotoPkDTO {

    @NotNull(message = "id_associado é um campo obrigatório")
    @JsonProperty(value = "id_associado")
    private Long idAssociado;

    @NotNull(message = "id_sessao é um campo obrigatório")
    @JsonProperty(value = "id_sessao")
    private Long idSessao;

}
