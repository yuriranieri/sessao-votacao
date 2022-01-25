package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.business.VotoBusiness;
import br.com.sicredi.sessaovotacao.dto.VotoRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarListVotoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoRelatorioDTO;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoRequestDTO;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarVotoResponseDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotoController.class)
class VotoControllerTest {

    private static final String URL = "/votos";
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @MockBean
    private VotoBusiness business;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void quandoSalvarVoto_retornaSucesso() throws Exception {
        when(business.salvar(any(VotoRequestDTO.class)))
                .thenReturn(criarVotoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(criarVotoRequestDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void quandoListarVotos_retornaSucesso() throws Exception {
        when(business.listar())
                .thenReturn(criarListVotoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoListarVotosPorIdSessao_retornaSucesso() throws Exception {
        Long idSessao = anyLong();

        when(business.listarPorIdSessao(idSessao))
                .thenReturn(criarListVotoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/sessoes/{idSessao}"), idSessao)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoListarVotosPorIdAssociado_retornaSucesso() throws Exception {
        Long idAssociado = anyLong();

        when(business.listarPorIdAssociado(idAssociado))
                .thenReturn(criarListVotoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/associado/{idAssociado}"), idAssociado)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoCalcularVotosDaSessao_retornaSucesso() throws Exception {
        Long idSessao = anyLong();

        when(business.calcularVotosDaSessao(idSessao))
                .thenReturn(criarVotoRelatorioDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/sessoes/{idSessao}/relatorio"), idSessao)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}