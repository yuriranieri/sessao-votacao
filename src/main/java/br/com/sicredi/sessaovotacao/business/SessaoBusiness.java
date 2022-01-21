package br.com.sicredi.sessaovotacao.business;

import br.com.sicredi.sessaovotacao.converter.SessaoConverter;
import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.exception.ErrorBusinessException;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.service.PautaService;
import br.com.sicredi.sessaovotacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class SessaoBusiness {

    private final SessaoConverter converter;
    private final SessaoService service;
    private final PautaService pautaService;

    public SessaoResponseDTO salvar(SessaoRequestDTO requestDTO) {
        SessaoEntity entity = converter.requestDtoToEntity(requestDTO);
        Long idPauta = requestDTO.getIdPauta();
        LocalDateTime dateTimeNow = LocalDateTime.now();

        validaSessaoPorPauta(idPauta);
        entity.setDataInicio(dateTimeNow);
        entity.setDataFinal(calculaDataFinal(dateTimeNow, requestDTO.getTempoExpiracaoEmMinutos()));
        entity.setPauta(getPauta(idPauta));

        log.info("salvar - {}", entity);
        return converter.toResponseDto(service.salvar(entity));
    }

    public List<SessaoResponseDTO> listar() {
        return converter.toListResponseDto(service.listar());
    }

    public SessaoResponseDTO buscarPorId(Long id) {
        log.info("buscar por id - {}", id);
        return converter.toResponseDto(service.buscarPorId(id));
    }

    public SessaoResponseDTO buscarPorIdPauta(Long idPauta) {
        log.info("buscar por idPauta - {}", idPauta);
        SessaoEntity entity = getSessaoEntityPorPauta(idPauta)
                .orElseThrow(() -> new ErrorBusinessException(
                        String.format("Não existe sessão para a pauta %d", idPauta)));
        return converter.toResponseDto(entity);
    }

    private void validaSessaoPorPauta(Long idPauta) {
        getSessaoEntityPorPauta(idPauta)
                .ifPresent(sessao -> {
                    throw new ErrorBusinessException(String.format("Já existe sessão com a pauta %d", idPauta));
                });
    }

    private Optional<SessaoEntity> getSessaoEntityPorPauta(Long idPauta) {
        return service.buscarPorIdPauta(idPauta);
    }

    private PautaEntity getPauta(Long idPauta) {
        return pautaService.buscarPorId(idPauta);
    }

    private LocalDateTime calculaDataFinal(LocalDateTime dateTimeNow, int tempoExpiracaoEmMinutos) {
        return dateTimeNow.plusMinutes(tempoExpiracaoEmMinutos);
    }
}
