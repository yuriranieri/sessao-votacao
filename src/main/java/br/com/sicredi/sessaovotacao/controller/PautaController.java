package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.converter.PautaConverter;
import br.com.sicredi.sessaovotacao.dto.PautaRequestDTO;
import br.com.sicredi.sessaovotacao.dto.PautaResponseDTO;
import br.com.sicredi.sessaovotacao.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/pautas")
public class PautaController {

    private final PautaService service;
    private final PautaConverter converter;

    @Operation(summary = "Cadastrar pauta")
    @PostMapping
    public ResponseEntity<PautaResponseDTO> salvar(@RequestBody @Valid PautaRequestDTO requestDTO) {
        return ResponseEntity
                .status(CREATED)
                .body(converter.toResponseDto(
                        service.salvar(converter.requestDtoToEntity(requestDTO))));
    }

    @Operation(summary = "Listar pautas")
    @GetMapping
    public ResponseEntity<List<PautaResponseDTO>> listar() {
        return ResponseEntity
                .status(OK)
                .body(converter.toListResponseDto(service.listar()));
    }

    @Operation(summary = "Buscar pauta pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<PautaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(service.buscarPorId(id)));
    }

    @Operation(summary = "Atualizar informações de uma pauta pelo id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<PautaResponseDTO> atualizar(@RequestBody @Valid PautaRequestDTO requestDTO,
                                                      @PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(
                        service.atualizar(converter.requestDtoToEntity(requestDTO), id)));
    }

    @Operation(summary = "Deletar pauta pelo id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
