package br.com.sicredi.sessaovotacao.utils;

import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaEntity;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaEntityNoArgs;
import static br.com.sicredi.sessaovotacao.utils.PautaUtils.criarPautaResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.getPageable;
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
                .pautaResponseDTO(criarPautaResponseDTO())
                .build();
    }

    public static SessaoResponseDTO criarSessaoResponseDtoComPautaNull() {
        LocalDateTime dataInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 10));
        return SessaoResponseDTO.builder()
                .id(1L)
                .dataInicio(dataInicio)
                .dataFinal(dataInicio.plusMinutes(1))
                .pautaResponseDTO(null)
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

    public static SessaoEntity criarSessaoEntityComPautaNull() {
        LocalDateTime dataInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 10));
        return SessaoEntity.builder()
                .id(1L)
                .dataInicio(dataInicio)
                .dataFinal(dataInicio.plusMinutes(1))
                .pauta(null)
                .build();
    }

    public static SessaoEntity criarSessaoEntityNoArgs() {
        LocalDateTime dataInicio = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 10));
        SessaoEntity entity = new SessaoEntity();
        entity.setId(1L);
        entity.setDataInicio(dataInicio);
        entity.setDataFinal(dataInicio.plusMinutes(1));
        entity.setPauta(criarPautaEntityNoArgs());
        return entity;
    }

    public static List<SessaoResponseDTO> criarListSessaoResponseDTO() {
        return singletonList(criarSessaoResponseDTO());
    }

    public static List<SessaoEntity> criarListSessaoEntity() {
        return singletonList(criarSessaoEntity());
    }

    public static Page<SessaoResponseDTO> criarPageSessaoResponseDTO() {
        return new PageImpl<>(criarListSessaoResponseDTO(), getPageable(), 1L);
    }

    public static Page<SessaoEntity> criarPageSessaoEntity() {
        return new PageImpl<>(criarListSessaoEntity(), getPageable(), 1L);
    }

}
