package br.com.sicredi.sessaovotacao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PautaResponseDTO {

    private Long id;
    private String assunto;
    private String descricao;

}
