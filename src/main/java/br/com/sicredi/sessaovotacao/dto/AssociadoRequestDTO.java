package br.com.sicredi.sessaovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class AssociadoRequestDTO {

    @NotBlank(message = "cpf é um campo obrigatório")
    @Size(min = 11, max = 11)
    @CPF(message = "CPF inválido")
    private String cpf;

}
