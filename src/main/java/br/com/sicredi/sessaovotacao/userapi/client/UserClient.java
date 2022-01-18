package br.com.sicredi.sessaovotacao.userapi.client;

import br.com.sicredi.sessaovotacao.config.ValuesConfig;
import br.com.sicredi.sessaovotacao.userapi.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class UserClient {

    private final RestTemplate rest;
    private final ValuesConfig valuesConfig;

    public UserDTO carregarEntidade(String cpf) {
        return rest.getForObject(valuesConfig.getUrlUser() + "/" + cpf, UserDTO.class);
    }

}
