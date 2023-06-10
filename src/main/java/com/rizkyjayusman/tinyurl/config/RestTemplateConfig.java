package com.rizkyjayusman.tinyurl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000); // Set the connection timeout to 5 seconds
        requestFactory.setReadTimeout(10000); // Set the read timeout to 5 seconds
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }

}
