package br.com.sicredi.sessaovotacao.business;

import br.com.sicredi.sessaovotacao.converter.VotoConverter;
import br.com.sicredi.sessaovotacao.dto.VotoRelatorioDTO;
import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.VotoResponseDTO;
import br.com.sicredi.sessaovotacao.exception.ErrorBusinessException;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.model.VotoEntity;
import br.com.sicredi.sessaovotacao.model.VotoPK;
import br.com.sicredi.sessaovotacao.service.AssociadoService;
import br.com.sicredi.sessaovotacao.service.SessaoService;
import br.com.sicredi.sessaovotacao.service.VotoService;
import br.com.sicredi.sessaovotacao.userapi.client.UserClient;
import br.com.sicredi.sessaovotacao.userapi.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static br.com.sicredi.sessaovotacao.enumeration.VotoEnum.SIM;
import static br.com.sicredi.sessaovotacao.userapi.dto.StatusEnum.UNABLE_TO_VOTE;

@Slf4j
@RequiredArgsConstructor
@Component
public class VotoBusiness {

    private final VotoConverter converter;
    private final VotoService service;
    private final UserClient userClient;
    private final AssociadoService associadoService;
    private final SessaoService sessaoService;

    public VotoResponseDTO salvar(VotoRequestDTO requestDTO) {
        VotoEntity entity = converter.requestDtoToEntity(requestDTO);
        validaSeAssociadoVotouAnteriomente(requestDTO.getIdAssociado(), requestDTO.getIdSessao());
        entity.setAssociado(carregarAssociadoEntity(requestDTO.getIdAssociado()));

        SessaoEntity sessaoEntity = carregarSessaoEntity(requestDTO.getIdSessao());
        validarDataFimSessao(sessaoEntity.getDataFinal());
        entity.setSessao(sessaoEntity);

        log.info("salvar - {}", entity);
        return converter.toResponseDto(service.salvar(entity));
    }

    public Page<VotoResponseDTO> listar(Pageable pageable) {
        return converter.toPageResponseDto(service.listar(pageable));
    }

    public List<VotoResponseDTO> listarPorIdSessao(Long idSessao) {
        log.info("listar por idSessao - {}", idSessao);
        return converter.toListResponseDto(service.listarPorIdSessao(idSessao));
    }

    public Page<VotoResponseDTO> listarPorIdAssociado(Long idAssociado, Pageable pageable) {
        log.info("listar por idAssociado - {}", idAssociado);
        return converter.toPageResponseDto(service.listarPorIdAssociado(idAssociado, pageable));
    }

    public VotoRelatorioDTO calcularVotosDaSessao(Long idSessao) {
        log.info("calcular votos da sessao por idSessao - {}", idSessao);
        SessaoEntity sessaoEntity = carregarSessaoEntity(idSessao);

        if (hojeNaoForDepoisDataFimSessao(sessaoEntity.getDataFinal())) {
            throw new ErrorBusinessException("Sessão em andamento espere a finalização dela");
        }

        List<VotoResponseDTO> votos = listarPorIdSessao(idSessao);
        long quantidadeVotosSim = votos.stream()
                .filter(voto -> Objects.equals(SIM.name(), voto.getVoto()))
                .count();
        long quantidadeVotosNao = votos.size() - quantidadeVotosSim;

        return new VotoRelatorioDTO(quantidadeVotosSim, quantidadeVotosNao);
    }

    private void validaSeAssociadoVotouAnteriomente(Long idAssociado, Long idSessao) {
        service.buscarPorId(new VotoPK(idAssociado, idSessao))
                .ifPresent(voto -> {
                    VotoPK id = voto.getId();
                    throw new ErrorBusinessException(
                            String.format("Associado %d já votou na sessão %d",
                                    id.getIdAssociado(),
                                    id.getIdSessao()));
                });
    }

    private AssociadoEntity carregarAssociadoEntity(Long idAssociado) {
        AssociadoEntity associadoEntity = associadoService.buscarPorId(idAssociado);
        UserDTO userDTO = userClient.carregarEntidade(associadoEntity.getCpf());

        if (associadoNaoPuderVotar(userDTO)) {
            throw new ErrorBusinessException(
                    String.format("Associado com o cpf %s não pode votar", associadoEntity.getCpf()));
        }

        return associadoEntity;
    }

    private boolean associadoNaoPuderVotar(UserDTO userDTO) {
        return Objects.equals(userDTO.getStatus(), UNABLE_TO_VOTE.getDescricao());
    }

    private SessaoEntity carregarSessaoEntity(Long idSessao) {
        return sessaoService.buscarPorId(idSessao);
    }

    private void validarDataFimSessao(LocalDateTime dataFinal) {
        if (hojeForDepoisDataFimSessao(dataFinal)) {
            throw new ErrorBusinessException("Sessão foi expirada");
        }
    }

    private boolean hojeForDepoisDataFimSessao(LocalDateTime dataFinal) {
        return LocalDateTime.now().isAfter(dataFinal);
    }

    private boolean hojeNaoForDepoisDataFimSessao(LocalDateTime dataFinal) {
        return !hojeForDepoisDataFimSessao(dataFinal);
    }

}
