package br.com.sicredi.sessaovotacao.business;

import br.com.sicredi.sessaovotacao.converter.SessaoConverter;
import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import br.com.sicredi.sessaovotacao.model.SessaoEntity;
import br.com.sicredi.sessaovotacao.service.PautaService;
import br.com.sicredi.sessaovotacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SessaoBusiness {

    private final SessaoConverter converter;
    private final SessaoService service;
    private final PautaService pautaService;

    public SessaoResponseDTO salvar(SessaoRequestDTO requestDTO) {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        SessaoEntity entity = converter.requestDtoToEntity(requestDTO);
        entity.setDataInicio(dateTimeNow);
        entity.setDataFinal(dateTimeNow.plusMinutes(requestDTO.getTempoExpiracaoEmMinutos()));
        entity.setPauta(pautaService.buscarPorId(requestDTO.getIdPauta()));

        return converter.toResponseDto(service.salvar(entity));
    }

    public List<SessaoResponseDTO> listar() {
        return converter.toListResponseDto(service.listar());
    }

    public SessaoResponseDTO buscarPorId(Long id) {
        return converter.toResponseDto(service.buscarPorId(id));
    }
}
