package br.com.sicredi.sessaovotacao.utils;

import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaEntity;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaResponseDTO;
import static java.util.Collections.singletonList;

public abstract class SessaoUtils {

    public static SessaoRequestDTO criarSessaoRequestDTO() {
        return SessaoRequestDTO.builder()
                .idPauta(1L)
                .tempoExpiracaoEmMinutos(1)
                .build();
    }

    public static SessaoResponseDTO criarSessaoResponseDTO() {
        LocalDateTime dataInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 10));
        return SessaoResponseDTO.builder()
                .id(1L)
                .dataInicio(dataInicio)
                .dataFinal(dataInicio.plusMinutes(1))
                .tempoExpiracaoEmMinutos(1L)
                .pautaResponseDTO(criarPautaResponseDTO())
                .build();
    }

    public static SessaoEntity criarSessaoEntity() {
        LocalDateTime dataInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 10));
        return SessaoEntity.builder()
                .id(1L)
                .dataInicio(dataInicio)
                .dataFinal(dataInicio.plusMinutes(1))
                .pauta(criarPautaEntity())
                .build();
    }

    public static List<SessaoResponseDTO> criarListSessaoResponseDTO() {
        return singletonList(criarSessaoResponseDTO());
    }

    public static List<SessaoEntity> criarListSessaoEntity() {
        return singletonList(criarSessaoEntity());
    }

}
