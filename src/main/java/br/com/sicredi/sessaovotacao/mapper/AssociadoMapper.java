package br.com.sicredi.sessaovotacao.mapper;

import br.com.sicredi.sessaovotacao.dto.AssociadoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.AssociadoResponseDTO;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface AssociadoMapper {

    AssociadoEntity requestDtoToEntity(AssociadoRequestDTO requestDTO);

    AssociadoResponseDTO toResponseDto(AssociadoEntity entity);

    default Page<AssociadoResponseDTO> toPageResponseDto(Page<AssociadoEntity> entities) {
        return entities.map(this::toResponseDto);
    }

}
