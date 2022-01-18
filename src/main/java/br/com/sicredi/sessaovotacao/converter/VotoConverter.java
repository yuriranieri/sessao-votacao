package br.com.sicredi.sessaovotacao.converter;

import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.VotoResponseDTO;
import br.com.sicredi.sessaovotacao.enumeration.VotoEnum;
import br.com.sicredi.sessaovotacao.exception.ErrorBusinessException;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.model.VotoEntity;
import br.com.sicredi.sessaovotacao.model.VotoPK;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class VotoConverter {

    private final AssociadoConverter associadoConverter;
    private final SessaoConverter sessaoConverter;

    public VotoEntity requestDtoToEntity(VotoRequestDTO requestDTO) {
        Long idAssociado = requestDTO.getIdAssociado();
        Long idSessao = requestDTO.getIdSessao();

        return VotoEntity.builder()
                .id(new VotoPK(idAssociado, idSessao))
                .associado(AssociadoEntity.builder().id(idAssociado).build())
                .sessao(SessaoEntity.builder().id(idSessao).build())
                .voto(validarVoto(requestDTO.getVoto()))
                .build();
    }

    private String validarVoto(Character voto) {
        return VotoEnum.findByName(voto)
                .orElseThrow(() -> new ErrorBusinessException("O associado só pode votar s para sim ou n para não"))
                .name();
    }

    public VotoResponseDTO toResponseDto(VotoEntity entity) {
        return VotoResponseDTO.builder()
                .id(entity.getId())
                .voto(entity.getVoto())
                .associado(associadoConverter.toResponseDto(entity.getAssociado()))
                .sessao(sessaoConverter.toResponseDto(entity.getSessao()))
                .build();
    }

    public List<VotoResponseDTO> toListResponseDto(List<VotoEntity> entities) {
        return entities.stream()
                .map(this::toResponseDto)
                .toList();
    }
}
