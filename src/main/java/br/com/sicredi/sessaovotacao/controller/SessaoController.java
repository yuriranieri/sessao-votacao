package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.business.SessaoBusiness;
import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping(path = "/sessoes")
public class SessaoController {

    private final SessaoBusiness business;

    @Operation(summary = "Cadastrar sessão")
    @PostMapping
    public ResponseEntity<SessaoResponseDTO> salvar(@RequestBody @Valid SessaoRequestDTO requestDTO) {
        return ResponseEntity
                .status(CREATED)
                .body(business.salvar(requestDTO));
    }

    @Operation(summary = "Listar sessões")
    @GetMapping
    public ResponseEntity<List<SessaoResponseDTO>> listar() {
        return ResponseEntity
                .status(OK)
                .body(business.listar());
    }

    @Operation(summary = "Buscar sessão pelo id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<SessaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(business.buscarPorId(id));
    }

    @Operation(summary = "Buscar sessão através de uma pauta")
    @GetMapping(path = "/pautas/{idPauta}")
    public ResponseEntity<SessaoResponseDTO> buscarPorIdPauta(@PathVariable Long idPauta) {
        return ResponseEntity
                .status(OK)
                .body(business.buscarPorIdPauta(idPauta));
    }

}
