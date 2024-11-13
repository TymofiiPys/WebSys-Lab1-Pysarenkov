package org.example.websyslab1_proj.service;

import org.example.websyslab1_proj.dto.BBQuoteDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class BBService {
    private final RestClient restClient;
    public BBService()
    {
        this.restClient = RestClient.create("https://api.breakingbadquotes.xyz/v1");
    }

    @Cacheable(value = "breakingbadquote", key = "#n")
    public BBQuoteDTO[] getNQuotes(int n) {
        return restClient.get().uri("/quotes/" + n).retrieve().body(BBQuoteDTO[].class);
    }

    public BBQuoteDTO[] getQuote() {
        return restClient.get().uri("/quotes").retrieve().body(BBQuoteDTO[].class);
    }

    @Cacheable(value = "slowerquote", key = "#time")
    public BBQuoteDTO[] getQuoteTimeout(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return restClient.get().uri("/quotes").retrieve().body(BBQuoteDTO[].class);
    }
}
