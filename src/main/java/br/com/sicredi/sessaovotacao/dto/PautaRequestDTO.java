package br.com.sicredi.sessaovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class PautaRequestDTO {

    @NotBlank(message = "assunto é um campo obrigatório")
    private String assunto;

    @NotBlank(message = "descricao é um campo obrigatório")
    private String descricao;

}
