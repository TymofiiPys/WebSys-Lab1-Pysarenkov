package org.example.websyslab1_proj.controller;

import org.example.websyslab1_proj.entity.Resp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/products/{id}")
    public Resp index(@PathVariable(name="id") int id) {
        Resp ret = new Resp();
        ret.id = id;
        ret.name = id + " name";
        return ret;
    }
}
