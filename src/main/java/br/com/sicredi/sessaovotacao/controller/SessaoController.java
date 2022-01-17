package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.business.SessaoBusiness;
import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import br.com.sicredi.sessaovotacao.dto.SessaoResponseDTO;
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
@RequestMapping(path = "/sessoes")
public class SessaoController {

    private final SessaoBusiness business;

    @PostMapping
    public ResponseEntity<SessaoResponseDTO> salvar(@RequestBody @Valid SessaoRequestDTO requestDTO) {
        return ResponseEntity
                .status(CREATED)
                .body(business.salvar(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<SessaoResponseDTO>> listar() {
        return ResponseEntity
                .status(OK)
                .body(business.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SessaoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(OK)
                .body(business.buscarPorId(id));
    }

    @GetMapping(path = "/pautas/{idPauta}")
    public ResponseEntity<SessaoResponseDTO> buscarPorIdPauta(@PathVariable Long idPauta) {
        return ResponseEntity
                .status(OK)
                .body(business.buscarPorIdPauta(idPauta));
    }

}
