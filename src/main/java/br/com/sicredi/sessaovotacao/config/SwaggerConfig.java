package br.com.sicredi.sessaovotacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("sessao-votacao-public")
                .packagesToScan("br.com.sicredi.sessaovotacao.controller")
                .build();
    }

    @Bean
    public OpenAPI votacaoOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Votação API")
                        .description("API para gerenciar votações de associados em pautas através de sessões.")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                );
    }

}
