package br.com.sicredi.sessaovotacao.converter;

import br.com.sicredi.sessaovotacao.dto.AssociadoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.AssociadoResponseDTO;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssociadoConverter {

    public AssociadoEntity requestDtoToEntity(AssociadoRequestDTO requestDTO) {
        return AssociadoEntity.builder()
                .cpf(requestDTO.getCpf())
                .build();
    }

    public AssociadoResponseDTO toResponseDto(AssociadoEntity entity) {
        return AssociadoResponseDTO.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .build();
    }

    public List<AssociadoResponseDTO> toListResponseDto(List<AssociadoEntity> entities) {
        return entities.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
}
