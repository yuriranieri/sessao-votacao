package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.business.SessaoBusiness;
import br.com.sicredi.sessaovotacao.dto.SessaoRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarPageSessaoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoRequestDTO;
import static br.com.sicredi.sessaovotacao.utils.SessaoUtils.criarSessaoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.VotoUtils.getPageable;
import static java.util.Objects.requireNonNull;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessaoController.class)
class SessaoControllerTest {

    private static final String URL = "/sessoes";
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @MockBean
    private SessaoBusiness business;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void quandoSalvarSessao_retornaSucesso() throws Exception {
        when(business.salvar(any(SessaoRequestDTO.class)))
                .thenReturn(criarSessaoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(criarSessaoRequestDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void quandoSalvarSessao_retornaErroInvalidFormatException() throws Exception {
        String request = "{\n" +
                "    \"id_pauta\": 1,\n" +
                "    \"tempo_expiracao_em_minutos\": \"teste\",\n" +
                "}";
        String msg = "O campo 'tempo_expiracao_em_minutos' esperava receber o tipo 'int', " +
                "mas recebeu o valor 'teste' com o tipo 'String'";

        when(business.salvar(any(SessaoRequestDTO.class)))
                .thenReturn(criarSessaoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(APPLICATION_JSON)
                .content(request);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(
                        requireNonNull(result.getResolvedException()).getCause() instanceof InvalidFormatException))
                .andExpect(jsonPath("$.errors[0].message", is(msg)));
    }

    @Test
    void quandoListarSessao_retornaSucesso() throws Exception {
        when(business.listar(getPageable()))
                .thenReturn(criarPageSessaoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL)
                .contentType(APPLICATION_JSON)
                .param("page", "0")
                .param("size", "1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    void quandoBuscarSessaoPorId_retornaSucesso() throws Exception {
        Long id = anyLong();
        when(business.buscarPorId(id))
                .thenReturn(criarSessaoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/{id}"), id)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoBuscarSessaoPorIdPauta_retornaSucesso() throws Exception {
        Long id = anyLong();
        when(business.buscarPorIdPauta(id))
                .thenReturn(criarSessaoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/pautas/{id}"), id)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}