package br.com.sicredi.sessaovotacao.controller;

import br.com.sicredi.sessaovotacao.converter.AssociadoConverter;
import br.com.sicredi.sessaovotacao.dto.AssociadoRequestDTO;
import br.com.sicredi.sessaovotacao.exception.ErrorBusinessException;
import br.com.sicredi.sessaovotacao.exception.NotFoundException;
import br.com.sicredi.sessaovotacao.model.AssociadoEntity;
import br.com.sicredi.sessaovotacao.service.AssociadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoEntity;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoRequestDTO;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarAssociadoResponseDTO;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarPageAssociadoEntity;
import static br.com.sicredi.sessaovotacao.utils.AssociadoUtils.criarPageAssociadoResponseDTO;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociadoController.class)
class AssociadoControllerTest {

    private static final String URL = "/associados";
    private final ObjectMapper jsonMapper = new ObjectMapper();

    @MockBean
    private AssociadoService service;

    @MockBean
    private AssociadoConverter converter;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void quandoSalvar_retornaSucesso() throws Exception {
        AssociadoEntity entity = criarAssociadoEntity();
        AssociadoRequestDTO requestDTO = criarAssociadoRequestDTO();
        requestDTO.setCpf("74758697094");

        when(converter.requestDtoToEntity(any(AssociadoRequestDTO.class)))
                .thenReturn(entity);
        when(service.salvar(any(AssociadoEntity.class)))
                .thenReturn(entity);
        when(converter.toResponseDto(any(AssociadoEntity.class)))
                .thenReturn(criarAssociadoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(requestDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void quandoSalvar_retornaErroBadRequestMethodArgumentNotValidException() throws Exception {
        AssociadoEntity entity = criarAssociadoEntity();

        when(converter.requestDtoToEntity(any(AssociadoRequestDTO.class)))
                .thenReturn(entity);
        when(service.salvar(any(AssociadoEntity.class)))
                .thenReturn(entity);
        when(converter.toResponseDto(any(AssociadoEntity.class)))
                .thenReturn(criarAssociadoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(criarAssociadoRequestDTO()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void quandoSalvar_retornaErroBadRequestErrorBusinessException() throws Exception {
        AssociadoRequestDTO requestDTO = criarAssociadoRequestDTO();
        requestDTO.setCpf("74758697094");

        when(converter.requestDtoToEntity(any(AssociadoRequestDTO.class)))
                .thenReturn(criarAssociadoEntity());
        when(service.salvar(any(AssociadoEntity.class)))
                .thenThrow(new ErrorBusinessException(""));
        when(converter.toResponseDto(any(AssociadoEntity.class)))
                .thenReturn(criarAssociadoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(requestDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ErrorBusinessException));
    }

    @Test
    void quandoSalvar_retornaErroBadRequestHttpMessageNotReadableException() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(1L));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HttpMessageNotReadableException));
    }

    @Test
    void quandoListar_retornaSucesso() throws Exception {
        when(service.listar(any()))
                .thenReturn(criarPageAssociadoEntity());
        when(converter.toPageResponseDto(any()))
                .thenReturn(criarPageAssociadoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL)
                .contentType(APPLICATION_JSON)
                .param("page", "0")
                .param("size", "1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoBuscarPorId_retornaErroBadRequestNotFoundException() throws Exception {
        Long id = anyLong();
        when(service.buscarPorId(id))
                .thenThrow(new NotFoundException(""));

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/{id}"), id)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundException));
    }

    @Test
    void quandoBuscarPorId_retornaErroBadRequestTypeMismatchException() throws Exception {
        Long id = anyLong();

        when(service.buscarPorId(id))
                .thenThrow(new TypeMismatchException("aa", Long.class));

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/{id}"), id)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof TypeMismatchException));
    }

    @Test
    void quandoBuscarPorId_retornaSucesso() throws Exception {
        Long id = anyLong();

        when(service.buscarPorId(id))
                .thenReturn(criarAssociadoEntity());
        when(converter.toResponseDto(any(AssociadoEntity.class)))
                .thenReturn(criarAssociadoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = get(URL.concat("/{id}"), id)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoAtualizar_retornaSucesso() throws Exception {
        AssociadoEntity entity = criarAssociadoEntity();
        AssociadoRequestDTO requestDTO = criarAssociadoRequestDTO();
        requestDTO.setCpf("74758697094");

        when(converter.requestDtoToEntity(any(AssociadoRequestDTO.class)))
                .thenReturn(entity);
        when(service.atualizar(any(AssociadoEntity.class), anyLong()))
                .thenReturn(entity);
        when(converter.toResponseDto(any(AssociadoEntity.class)))
                .thenReturn(criarAssociadoResponseDTO());

        MockHttpServletRequestBuilder requestBuilder = put(URL.concat("/{id}"), 1L)
                .contentType(APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(requestDTO));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoDeletar_retornaSucesso() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete(URL.concat("/{id}"), 1L)
                .contentType(APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}