package br.com.sicredi.sessaovotacao.mapper;

import br.com.sicredi.sessaovotacao.dto.PautaRequestDTO;
import br.com.sicredi.sessaovotacao.dto.PautaResponseDTO;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface PautaMapper {

    PautaEntity requestDtoToEntity(PautaRequestDTO requestDTO);

    PautaResponseDTO toResponseDto(PautaEntity entity);

    default Page<PautaResponseDTO> toPageResponseDto(Page<PautaEntity> entities) {
        return entities.map(this::toResponseDto);
    }

}
