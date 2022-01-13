package br.com.sicredi.sessaovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PautaRequestDTO {

    @NotBlank(message = "assunto é um campo obrigatório")
    private String assunto;

    @NotBlank(message = "descricao é um campo obrigatório")
    private String descricao;

}
