package br.com.sicredi.sessaovotacao.converter;

import br.com.sicredi.sessaovotacao.dto.PautaRequestDTO;
import br.com.sicredi.sessaovotacao.dto.PautaResponseDTO;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PautaConverter {

    public PautaEntity requestDtoToEntity(PautaRequestDTO requestDTO) {
        return PautaEntity.builder()
                .assunto(requestDTO.getAssunto())
                .descricao(requestDTO.getDescricao())
                .build();
    }

    public PautaResponseDTO toResponseDto(PautaEntity entity) {
        return PautaResponseDTO.builder()
                .id(entity.getId())
                .assunto(entity.getAssunto())
                .descricao(entity.getDescricao())
                .build();
    }

    public List<PautaResponseDTO> toListResponseDto(List<PautaEntity> entities) {
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

}
