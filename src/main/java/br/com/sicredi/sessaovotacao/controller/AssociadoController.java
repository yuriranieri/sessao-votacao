package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.converter.AssociadoConverter;
import br.com.sicredi.sessaovotacao.dto.AssociadoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.AssociadoResponseDTO;
import br.com.sicredi.sessaovotacao.service.AssociadoService;
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
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/associados")
public class AssociadoController {

    private final AssociadoService service;
    private final AssociadoConverter converter;

    @Operation(summary = "Cadastrar associado")
    @PostMapping
    public ResponseEntity<AssociadoResponseDTO> salvar(@RequestBody @Valid AssociadoRequestDTO requestDTO) {
        log.info("salvar - {}", requestDTO);
        return ResponseEntity
                .status(CREATED)
                .body(converter.toResponseDto(
                        service.salvar(converter.requestDtoToEntity(requestDTO))));
    }

    @Operation(summary = "Listar associados")
    @GetMapping
    public ResponseEntity<Page<AssociadoResponseDTO>> listar(@RequestParam int page, @RequestParam int size) {
        log.info("listar");
        return ResponseEntity
                .status(OK)
                .body(converter.toPageResponseDto(service.listar(PageRequest.of(page, size, ASC, "id"))));
    }

    @Operation(summary = "Buscar associado pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<AssociadoResponseDTO> buscarPorId(@PathVariable Long id) {
        log.info("buscar por id - {}", id);
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(service.buscarPorId(id)));
    }

    @Operation(summary = "Atualizar informações de um associado pelo id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<AssociadoResponseDTO> atualizar(@RequestBody @Valid AssociadoRequestDTO requestDTO,
                                                          @PathVariable Long id) {
        log.info("atualizar por id - {} {}", requestDTO, id);
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(
                        service.atualizar(converter.requestDtoToEntity(requestDTO), id)));
    }

    @Operation(summary = "Deletar associado pelo id")
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        log.info("deletar por id - {}", id);
        service.deletarPorId(id);
    }

}
