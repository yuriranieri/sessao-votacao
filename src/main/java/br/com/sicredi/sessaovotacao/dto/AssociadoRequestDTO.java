package br.com.sicredi.sessaovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AssociadoRequestDTO {

    @NotBlank(message = "cpf é um campo obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

}
