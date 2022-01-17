package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.converter.PautaConverter;
import br.com.sicredi.sessaovotacao.dto.PautaRequestDTO;
import br.com.sicredi.sessaovotacao.model.PautaEntity;
import br.com.sicredi.sessaovotacao.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static br.com.sicredi.sessaovotacao.utils.PautaUtils.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PautaController.class)
class PautaControllerTest {

    private static final String URL = "/pautas";
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @MockBean
    private PautaService service;

    @MockBean
    private PautaConverter converter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void quandoSalvarPauta_retornaSucesso() throws Exception {
        PautaEntity entity = criarPautaEntity();

        when(converter.requestDtoToEntity(any(PautaRequestDTO.class)))
                .thenReturn(entity);
        when(service.salvar(any(PautaEntity.class)))
                .thenReturn(entity);
        when(converter.toResponseDto(any(PautaEntity.class)))
                .thenReturn(criarPautaResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(criarPautaRequestDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void quandoListarPauta_retornaSucesso() throws Exception {
        when(service.listar())
                .thenReturn(criarListPautaEntity());
        when(converter.toListResponseDto(anyList()))
                .thenReturn(criarListPautaResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoBuscarPautaPorId_retornaSucesso() throws Exception {
        Long id = anyLong();

        when(service.buscarPorId(id))
                .thenReturn(criarPautaEntity());
        when(converter.toResponseDto(any(PautaEntity.class)))
                .thenReturn(criarPautaResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/{id}"), id)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoAtualizarPauta_retornaSucesso() throws Exception {
        PautaEntity entity = criarPautaEntity();

        when(converter.requestDtoToEntity(any(PautaRequestDTO.class)))
                .thenReturn(entity);
        when(service.atualizar(any(PautaEntity.class), anyLong()))
                .thenReturn(entity);
        when(converter.toResponseDto(any(PautaEntity.class)))
                .thenReturn(criarPautaResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = put(URL.concat("/{id}"), 1L)
                .contentType(APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(criarPautaRequestDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoDeletarPautaPorId_retornaSucesso() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete(URL.concat("/{id}"), 1L)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}