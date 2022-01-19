package br.com.sicredi.sessaovotacao.converter;

import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SessaoConverter {

    private final PautaConverter pautaConverter;

    public SessaoEntity requestDtoToEntity(SessaoRequestDTO requestDTO) {
        return SessaoEntity.builder()
                .pauta(PautaEntity.builder().id(requestDTO.getIdPauta()).build())
                .build();
    }

    public SessaoResponseDTO toResponseDto(SessaoEntity entity) {
        LocalDateTime dataInicio = entity.getDataInicio();
        LocalDateTime dataFinal = entity.getDataFinal();
        return SessaoResponseDTO.builder()
                .id(entity.getId())
                .dataInicio(dataInicio)
                .dataFinal(dataFinal)
                .tempoExpiracaoEmMinutos(ChronoUnit.MINUTES.between(dataInicio, dataFinal))
                .pautaResponseDTO(pautaConverter.toResponseDto(entity.getPauta()))
                .build();
    }

    public List<SessaoResponseDTO> toListResponseDto(List<SessaoEntity> entities) {
        return entities.stream()
                .map(this::toResponseDto)
                .toList();
    }

}
