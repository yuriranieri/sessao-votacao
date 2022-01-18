package br.com.sicredi.sessaovotacao.userapi.client;

import br.com.sicredi.sessaovotacao.config.ValuesConfig;
import br.com.sicredi.sessaovotacao.userapi.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static br.com.sicredi.sessaovotacao.utils.VotoUtils.criarUserDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserClientTest {

    @InjectMocks
    private UserClient client;

    @Mock
    private RestTemplate rest;

    @Mock
    private ValuesConfig valuesConfig;

    @Test
    void quandoCarregarEntidade_retornaSucesso() {
        UserDTO userDTO = criarUserDTO();

        when(valuesConfig.getUrlUser())
                .thenReturn("localhost");
        when(rest.getForObject("localhost/31426540035", UserDTO.class))
                .thenReturn(userDTO);

        assertThat(client.carregarEntidade("31426540035"))
                .isNotNull()
                .isEqualTo(userDTO);
    }
}