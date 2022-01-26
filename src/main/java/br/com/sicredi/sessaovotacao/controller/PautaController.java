package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.converter.PautaConverter;
import br.com.sicredi.sessaovotacao.dto.PautaRequestDTO;
import br.com.sicredi.sessaovotacao.dto.PautaResponseDTO;
import br.com.sicredi.sessaovotacao.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/pautas")
public class PautaController {

    private final PautaService service;
    private final PautaConverter converter;

    @Operation(summary = "Cadastrar pauta")
    @PostMapping
    public ResponseEntity<PautaResponseDTO> salvar(@RequestBody @Valid PautaRequestDTO requestDTO) {
        log.info("salvar - {}", requestDTO);
        return ResponseEntity
                .status(CREATED)
                .body(converter.toResponseDto(
                        service.salvar(converter.requestDtoToEntity(requestDTO))));
    }

    @Operation(summary = "Listar pautas")
    @GetMapping
    public ResponseEntity<Page<PautaResponseDTO>> listar(@RequestParam int page, @RequestParam int size) {
        log.info("listar");
        return ResponseEntity
                .status(OK)
                .body(converter.toPageResponseDto(service.listar(PageRequest.of(page, size, ASC, "id"))));
    }

    @Operation(summary = "Buscar pauta pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<PautaResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("buscar por id - {}", id);
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(service.buscarPorId(id)));
    }

    @Operation(summary = "Atualizar informações de uma pauta pelo id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<PautaResponseDTO> atualizar(@RequestBody @Valid PautaRequestDTO requestDTO,
                                                      @PathVariable Long id) {
        log.info("atualizar por id - {} {}", requestDTO, id);
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(
                        service.atualizar(converter.requestDtoToEntity(requestDTO), id)));
    }

    @Operation(summary = "Deletar pauta pelo id")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        log.info("deletar por id - {}", id);
        service.deletarPorId(id);
    }

}
