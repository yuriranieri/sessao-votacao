package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.business.VotoBusiness;
import br.com.sicredi.sessaovotacao.dto.VotoRelatorioDTO;
import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.VotoResponseDTO;
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
@RequestMapping(path = "/votos")
public class VotoController {

    private final VotoBusiness business;

    @PostMapping
    public ResponseEntity<VotoResponseDTO> salvar(@RequestBody @Valid VotoRequestDTO requestDTO) {
        return ResponseEntity
                .status(CREATED)
                .body(business.salvar(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<VotoResponseDTO>> listar() {
        return ResponseEntity
                .status(OK)
                .body(business.listar());
    }

    @GetMapping("/sessoes/{idSessao}")
    public ResponseEntity<List<VotoResponseDTO>> listarPorIdSessao(@PathVariable Long idSessao) {
        return ResponseEntity
                .status(OK)
                .body(business.listarPorIdSessao(idSessao));
    }

    @GetMapping("/sessoes/{idSessao}/relatorio")
    public ResponseEntity<VotoRelatorioDTO> calcularVotosDaSessao(@PathVariable Long idSessao) {
        return ResponseEntity
                .status(OK)
                .body(business.calcularVotosDaSessao(idSessao));
    }

}
