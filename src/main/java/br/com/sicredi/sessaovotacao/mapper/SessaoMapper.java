package br.com.sicredi.sessaovotacao.mapper;

import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface SessaoMapper {

    @Mapping(source = "idPauta", target = "pauta.id")
    SessaoEntity requestDtoToEntity(SessaoRequestDTO requestDTO);

    @Mapping(source = "pauta", target = "pautaResponseDTO")
    SessaoResponseDTO toResponseDto(SessaoEntity entity);

    default Page<SessaoResponseDTO> toPageResponseDto(Page<SessaoEntity> entities) {
        return entities.map(this::toResponseDto);
    }

}
