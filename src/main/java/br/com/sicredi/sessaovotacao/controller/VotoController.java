package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.business.VotoBusiness;
import br.com.sicredi.sessaovotacao.dto.VotoRelatorioDTO;
import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.VotoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/votos")
public class VotoController {

    private final VotoBusiness business;

    @Operation(summary = "Cadastrar voto de um associado em uma sessão")
    @PostMapping
    public ResponseEntity<VotoResponseDTO> salvar(@RequestBody @Valid VotoRequestDTO requestDTO) {
        log.info("salvar - {}", requestDTO);
        return ResponseEntity
                .status(CREATED)
                .body(business.salvar(requestDTO));
    }

    @Operation(summary = "Listar os votos")
    @GetMapping
    public ResponseEntity<List<VotoResponseDTO>> listar() {
        log.info("listar");
        return ResponseEntity
                .status(OK)
                .body(business.listar());
    }

    @Operation(summary = "Listar os votos de uma sessão pelo idSessao")
    @GetMapping("/sessoes/{idSessao}")
    public ResponseEntity<List<VotoResponseDTO>> listarPorIdSessao(@PathVariable Long idSessao) {
        log.info("listar por idSessao - {}", idSessao);
        return ResponseEntity
                .status(OK)
                .body(business.listarPorIdSessao(idSessao));
    }

    @Operation(summary = "Listar os votos de um associado pelo idAssociado")
    @GetMapping("/associado/{idAssociado}")
    public ResponseEntity<List<VotoResponseDTO>> listarPorIdAssociado(@PathVariable Long idAssociado) {
        log.info("listar por idAssociado - {}", idAssociado);
        return ResponseEntity
                .status(OK)
                .body(business.listarPorIdAssociado(idAssociado));
    }

    @Operation(summary = "Carregar relatório de quantidade de votos (sim e não) de uma sessão")
    @GetMapping("/sessoes/{idSessao}/relatorio")
    public ResponseEntity<VotoRelatorioDTO> calcularVotosDaSessao(@PathVariable Long idSessao) {
        log.info("calcular votos da sessao por idSessao - {}", idSessao);
        return ResponseEntity
                .status(OK)
                .body(business.calcularVotosDaSessao(idSessao));
    }

}
