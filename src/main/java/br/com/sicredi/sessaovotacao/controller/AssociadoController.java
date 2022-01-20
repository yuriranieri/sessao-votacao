package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.converter.AssociadoConverter;
import br.com.sicredi.sessaovotacao.dto.AssociadoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.AssociadoResponseDTO;
import br.com.sicredi.sessaovotacao.service.AssociadoService;
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
@RequestMapping(path = "/associados")
public class AssociadoController {

    private final AssociadoService service;
    private final AssociadoConverter converter;

    @Operation(summary = "Cadastrar associado")
    @PostMapping
    public ResponseEntity<AssociadoResponseDTO> salvar(@RequestBody @Valid AssociadoRequestDTO requestDTO) {
        return ResponseEntity
                .status(CREATED)
                .body(converter.toResponseDto(
                        service.salvar(converter.requestDtoToEntity(requestDTO))));
    }

    @Operation(summary = "Listar associados")
    @GetMapping
    public ResponseEntity<List<AssociadoResponseDTO>> listar() {
        return ResponseEntity
                .status(OK)
                .body(converter.toListResponseDto(service.listar()));
    }

    @Operation(summary = "Buscar associado pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<AssociadoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(service.buscarPorId(id)));
    }

    @Operation(summary = "Atualizar informações de um associado pelo id")
    @PutMapping(path = "/{id}")
    public ResponseEntity<AssociadoResponseDTO> atualizar(@RequestBody @Valid AssociadoRequestDTO requestDTO,
                                                          @PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(
                        service.atualizar(converter.requestDtoToEntity(requestDTO), id)));
    }

    @Operation(summary = "Deletar associado pelo id")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
