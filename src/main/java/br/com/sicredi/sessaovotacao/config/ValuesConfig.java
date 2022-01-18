package br.com.sicredi.sessaovotacao.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ValuesConfig {

    @Value("${url.user-api}")
    private String urlUser;

}
