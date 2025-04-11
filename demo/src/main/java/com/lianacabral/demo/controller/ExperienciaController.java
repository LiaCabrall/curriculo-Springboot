package com.lianacabral.demo.controller;

import com.lianacabral.demo.model.Experiencia;
import com.lianacabral.demo.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiencias")
public class ExperienciaController {

    @Autowired
    private ExperienciaRepository experienciaRepository;

    @GetMapping
    public List<Experiencia> listar() {
        return experienciaRepository.findAll();
    }

    @PostMapping
    public Experiencia salvar(@RequestBody Experiencia experiencia) {
        return experienciaRepository.save(experiencia);
    }

    @GetMapping("/{id}")
    public Experiencia buscar(@PathVariable Long id) {
        return experienciaRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Experiencia atualizar(@PathVariable Long id, @RequestBody Experiencia novaExperiencia) {
        return experienciaRepository.findById(id).map(experiencia -> {
            experiencia.setCargo(novaExperiencia.getCargo());
            experiencia.setEmpresa(novaExperiencia.getEmpresa());
            experiencia.setDescricao(novaExperiencia.getDescricao());
            experiencia.setDataInicio(novaExperiencia.getDataInicio());
            experiencia.setDataFim(novaExperiencia.getDataFim());
            experiencia.setPessoa(novaExperiencia.getPessoa());
            return experienciaRepository.save(experiencia);
        }).orElse(null);
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        experienciaRepository.deleteById(id);
    }

}
