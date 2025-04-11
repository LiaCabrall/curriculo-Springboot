package com.lianacabral.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/")
    public String hello() {
        return "Olá, backend rodando!";
    }
}

