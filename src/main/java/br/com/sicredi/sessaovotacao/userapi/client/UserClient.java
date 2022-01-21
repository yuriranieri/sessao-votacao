package br.com.sicredi.sessaovotacao.userapi.client;

import br.com.sicredi.sessaovotacao.config.ValuesConfig;
import br.com.sicredi.sessaovotacao.userapi.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserClient {

    private final RestTemplate rest;
    private final ValuesConfig valuesConfig;

    public UserDTO carregarEntidade(String cpf) {
        log.info("buscar por cpf - {}", cpf);
        return rest.getForObject(valuesConfig.getUrlUser() + "/" + cpf, UserDTO.class);
    }

}
