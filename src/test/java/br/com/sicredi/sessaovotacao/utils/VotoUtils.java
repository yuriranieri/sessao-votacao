package br.com.sicredi.sessaovotacao.utils;

import br.com.sicredi.sessaovotacao.dto.VotoRelatorioDTO;
import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.VotoResponseDTO;
import br.com.sicredi.sessaovotacao.model.VotoEntity;
import br.com.sicredi.sessaovotacao.model.VotoPK;
import br.com.sicredi.sessaovotacao.userapi.dto.UserDTO;

import java.util.List;

import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoEntity;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoEntity;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoResponseDTO;
import static java.util.Collections.singletonList;

public abstract class VotoUtils {

    public static VotoRequestDTO criarVotoRequestDTO() {
        return VotoRequestDTO.builder()
                .idAssociado(1L)
                .idSessao(1L)
                .voto('s')
                .build();
    }

    public static VotoResponseDTO criarVotoResponseDTO() {
        return VotoResponseDTO.builder()
                .id(new VotoPK(1L, 1L))
                .voto("SIM")
                .associado(criarAssociadoResponseDTO())
                .sessao(criarSessaoResponseDTO())
                .build();
    }

    public static VotoEntity criarVotoEntity() {
        return VotoEntity.builder()
                .id(new VotoPK(1L, 1L))
                .associado(criarAssociadoEntity())
                .sessao(criarSessaoEntity())
                .voto("SIM")
                .build();
    }

    public static UserDTO criarUserDTO() {
        return new UserDTO("ABLE_TO_VOTE");
    }

    public static VotoRelatorioDTO criarVotoRelatorioDTO() {
        return new VotoRelatorioDTO(1L, 0L);
    }

    public static List<VotoResponseDTO> criarListVotoResponseDTO() {
        return singletonList(criarVotoResponseDTO());
    }

    public static List<VotoEntity> criarListVotoEntity() {
        return singletonList(criarVotoEntity());
    }

}
