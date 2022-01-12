package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.converter.AssociadoConverter;
import br.com.sicredi.sessaovotacao.dto.AssociadoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.AssociadoResponseDTO;
import br.com.sicredi.sessaovotacao.service.AssociadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/associados")
public class AssociadoController {

    private final AssociadoService service;
    private final AssociadoConverter converter;

    @PostMapping
    public ResponseEntity<AssociadoResponseDTO> salvar(@RequestBody @Valid AssociadoRequestDTO requestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converter.toResponseDto(
                        service.salvar(converter.requestDtoToEntity(requestDTO))));
    }

    @GetMapping
    public ResponseEntity<List<AssociadoResponseDTO>> listar() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converter.toListResponseDto(service.listar()));
    }

}
