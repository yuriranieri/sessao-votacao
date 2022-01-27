package br.com.sicredi.sessaovotacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class SessaoResponseDTO {

    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonProperty(value = "data_inicio")
    private LocalDateTime dataInicio;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonProperty(value = "data_final")
    private LocalDateTime dataFinal;

    @JsonProperty(value = "pauta")
    private PautaResponseDTO pautaResponseDTO;

}
