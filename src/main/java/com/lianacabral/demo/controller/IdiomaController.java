package com.lianacabral.demo.controller;

import com.lianacabral.demo.model.Idioma;
import com.lianacabral.demo.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/idiomas")
public class IdiomaController {

    @Autowired
    private IdiomaRepository idiomaRepository;

    @GetMapping
    public List<Idioma> listar() {
        return idiomaRepository.findAll();
    }

    @PostMapping
    public Idioma salvar(@RequestBody Idioma idioma) {
        return idiomaRepository.save(idioma);
    }

    @GetMapping("/{id}")
    public Idioma buscar(@PathVariable Long id) {
        return idiomaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Idioma atualizar(@PathVariable Long id, @RequestBody Idioma novoIdioma) {
        return idiomaRepository.findById(id).map(idioma -> {
            idioma.setNome(novoIdioma.getNome());
            idioma.setNivel(novoIdioma.getNivel());
            idioma.setPessoa(novoIdioma.getPessoa());
            return idiomaRepository.save(idioma);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        idiomaRepository.deleteById(id);
    }

}