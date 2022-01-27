package br.com.sicredi.sessaovotacao.mapper;

import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.VotoResponseDTO;
import br.com.sicredi.sessaovotacao.enumeration.VotoEnum;
import br.com.sicredi.sessaovotacao.exception.ErrorBusinessException;
import br.com.sicredi.sessaovotacao.model.VotoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VotoMapper {

    @Named("validarVoto")
    static String validarVoto(Character voto) {
        return VotoEnum.findByName(voto)
                .orElseThrow(() -> new ErrorBusinessException("O associado só pode votar s para sim ou n para não"))
                .name();
    }

    @Mapping(source = "id.idAssociado", target = "associado.id")
    @Mapping(source = "id.idSessao", target = "sessao.id")
    @Mapping(source = "voto", target = "voto", qualifiedByName = "validarVoto")
    VotoEntity requestDtoToEntity(VotoRequestDTO requestDTO);


    @Mapping(source = "sessao.pauta", target = "sessao.pautaResponseDTO")
    VotoResponseDTO toResponseDto(VotoEntity entity);

    List<VotoResponseDTO> toListResponseDto(List<VotoEntity> entities);

    default Page<VotoResponseDTO> toPageResponseDto(Page<VotoEntity> entities) {
        return entities.map(this::toResponseDto);
    }

}
