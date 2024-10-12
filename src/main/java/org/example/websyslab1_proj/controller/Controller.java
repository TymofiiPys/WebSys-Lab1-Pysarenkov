package org.example.websyslab1_proj.controller;

import org.example.websyslab1_proj.dto.BBQuoteDTO;
import org.example.websyslab1_proj.entity.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class Controller {
    private final RestClient restClient;
//    @GetMapping("/products/{id}")
    public Resp index(@PathVariable(name="id") int id) {
        Resp ret = new Resp();
        ret.id = id;
        ret.name = id + " name";
        return ret;
    }

    public Controller()
    {
        this.restClient = RestClient.create("https://api.breakingbadquotes.xyz/v1");
    }
    @GetMapping("/breakingbad/{number}")
    public ResponseEntity<BBQuoteDTO[]> breakingBadNQuotes(@PathVariable(name = "number") int number) {
        return ResponseEntity.ok(restClient.get().uri("/quotes/" + number).retrieve().body(BBQuoteDTO[].class));
    }
    @GetMapping("/breakingbad")
    public ResponseEntity<BBQuoteDTO[]> breakingBadRandomQuote() {
        return ResponseEntity.ok(restClient.get().uri("/quotes").retrieve().body(BBQuoteDTO[].class));
    }
}
