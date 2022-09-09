package com.santander;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Component
public class IdGenerator {

    static RestTemplate restTemplate;
    static HttpEntity<String> entity;
    private static IdGenerator idGenerator;
    HttpHeaders headers;

    private IdGenerator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        this.entity = new HttpEntity<String>(headers);

    }

    public static String getId() {

        if (idGenerator == null) {
            idGenerator = new IdGenerator(new RestTemplate());
        }
        String[] token = restTemplate.exchange("https://www.uuidgenerator.net/api/version4", HttpMethod.GET, entity, String[].class).getBody();
        return token[0];
    }

}
