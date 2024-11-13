package org.example.websyslab1_proj.controller;

import lombok.RequiredArgsConstructor;
import org.example.websyslab1_proj.dto.BBQuoteDTO;
import org.example.websyslab1_proj.entity.Resp;
import org.example.websyslab1_proj.service.BBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final BBService service;
//    @GetMapping("/products/{id}")
    public Resp index(@PathVariable(name="id") int id) {
        Resp ret = new Resp();
        ret.id = id;
        ret.name = id + " name";
        return ret;
    }

    @GetMapping("/breakingbad/{number}")
    public ResponseEntity<BBQuoteDTO[]> breakingBadNQuotes(@PathVariable(name = "number") int number, @RequestParam(name = "slow", required = false) Optional<Boolean> slow) {
        if(slow.isPresent() || slow.get()){
            return ResponseEntity.ok(service.getQuoteTimeout(number));
        }
        return ResponseEntity.ok(service.getNQuotes(number));
    }
    @GetMapping("/breakingbad")
    public ResponseEntity<BBQuoteDTO[]> breakingBadRandomQuote() {
        return ResponseEntity.ok(service.getQuote());
    }
}
