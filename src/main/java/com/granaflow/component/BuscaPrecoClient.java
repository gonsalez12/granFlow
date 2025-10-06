package com.granaflow.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class BuscaPrecoClient {

    private final RestTemplate restTemplate;



    public BuscaPrecoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getPreco(String ticker) {
        String url = "http://localhost:8081" + "/preco/" + ticker;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response != null && response.containsKey("preco")) {
            return ((Number) response.get("preco")).doubleValue();
        }
        return null;
    }
}

