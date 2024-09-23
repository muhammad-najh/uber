package com.skysoft.krd.uber.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    @Value("${osrm_base_url}")
    private String OSRM_BASE_URL;

    @Bean
    @Qualifier("getOSRMClient")
    public RestClient getOSRMClient(){
        return RestClient.builder()
                .baseUrl(OSRM_BASE_URL)
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError,(req,res)->{

                    throw new RuntimeException("Server Error Occur");

                }).build();

    }

}
