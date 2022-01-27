package br.com.sicredi.sessaovotacao.utils;

import br.com.sicredi.sessaovotacao.dto.VotoPkDTO;
import br.com.sicredi.sessaovotacao.dto.VotoRelatorioDTO;
import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.VotoResponseDTO;
import br.com.sicredi.sessaovotacao.model.VotoEntity;
import br.com.sicredi.sessaovotacao.model.VotoPK;
import br.com.sicredi.sessaovotacao.userapi.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoEntity;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoEntityNoArgs;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoEntity;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoEntityNoArgs;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoResponseDTO;
import static java.util.Collections.singletonList;
import static org.springframework.data.domain.Sort.Direction.ASC;

public abstract class VotoUtils {

    public static VotoRequestDTO criarVotoRequestDTO() {
        return VotoRequestDTO.builder()
                .id(new VotoPkDTO(1L, 1L))
                .voto('s')
                .build();
    }

    public static VotoResponseDTO criarVotoResponseDTO() {
        return VotoResponseDTO.builder()
                .id(criarVotoPkDTO())
                .voto("SIM")
                .associado(criarAssociadoResponseDTO())
                .sessao(criarSessaoResponseDTO())
                .build();
    }

    public static VotoEntity criarVotoEntity() {
        return VotoEntity.builder()
                .id(criarVotoPK())
                .associado(criarAssociadoEntity())
                .sessao(criarSessaoEntity())
                .voto("SIM")
                .build();
    }

    public static VotoEntity criarVotoEntityNoArgs() {
        VotoEntity entity = new VotoEntity();
        entity.setId(criarVotoPkNoArgs());
        entity.setAssociado(criarAssociadoEntityNoArgs());
        entity.setSessao(criarSessaoEntityNoArgs());
        entity.setVoto("SIM");
        return entity;
    }

    public static VotoPK criarVotoPK() {
        return new VotoPK(1L, 1L);
    }

    public static VotoPkDTO criarVotoPkDTO() {
        return new VotoPkDTO(1L, 1L);
    }

    public static VotoPK criarVotoPkNoArgs() {
        VotoPK pk = new VotoPK();
        pk.setIdAssociado(1L);
        pk.setIdSessao(1L);
        return pk;
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

    public static Pageable getPageable() {
        return PageRequest.of(0, 1, ASC, "id");
    }

    public static Page<VotoResponseDTO> criarPageVotoResponseDTO() {
        return new PageImpl<>(criarListVotoResponseDTO(), getPageable(), 1L);
    }

    public static Page<VotoEntity> criarPageVotoEntity() {
        return new PageImpl<>(criarListVotoEntity(), getPageable(), 1L);
    }

}
