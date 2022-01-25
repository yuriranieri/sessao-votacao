package br.com.sicredi.sessaovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class AssociadoResponseDTO {

    private Long id;
    private String cpf;

}
