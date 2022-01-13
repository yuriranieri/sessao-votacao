package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.converter.PautaConverter;
import br.com.sicredi.sessaovotacao.dto.PautaRequestDTO;
import br.com.sicredi.sessaovotacao.dto.PautaResponseDTO;
import br.com.sicredi.sessaovotacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<PautaResponseDTO> salvar(@RequestBody @Valid PautaRequestDTO requestDTO) {
        return ResponseEntity
                .status(CREATED)
                .body(converter.toResponseDto(
                        service.salvar(converter.requestDtoToEntity(requestDTO))));
    }

    @GetMapping
    public ResponseEntity<List<PautaResponseDTO>> listar() {
        return ResponseEntity
                .status(OK)
                .body(converter.toListResponseDto(service.listar()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PautaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(service.buscarPorId(id)));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PautaResponseDTO> atualizar(@RequestBody @Valid PautaRequestDTO requestDTO,
                                                      @PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(converter.toResponseDto(
                        service.atualizar(converter.requestDtoToEntity(requestDTO), id)));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}
