package br.com.sicredi.sessaovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class AssociadoResponseDTO {

    private Long id;
    private String cpf;

}
