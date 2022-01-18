package br.com.sicredi.sessaovotacao.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

import static java.util.Collections.singletonList;

@Configuration
public class RestTemplateConfig {

    private static final Integer CONNECTION_TIMEOUT = 25;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(singletonList(MediaType.APPLICATION_JSON));

        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT))
                .setReadTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT))
                .messageConverters(mappingJackson2HttpMessageConverter)
                .build();
    }

}
