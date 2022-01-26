package br.com.sicredi.sessaovotacao.converter;

import br.com.sicredi.sessaovotacao.dto.AssociadoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.AssociadoResponseDTO;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

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

    public Page<AssociadoResponseDTO> toPageResponseDto(Page<AssociadoEntity> entities) {
        return entities.map(this::toResponseDto);
    }
}
