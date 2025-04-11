package com.lianacabral.demo.controller;

import com.lianacabral.demo.model.Educacao;
import com.lianacabral.demo.repository.EducacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/educacoes")
public class EducacaoController {

    @Autowired
    private EducacaoRepository educacaoRepository;

    @GetMapping
    public List<Educacao> listar() {
        return educacaoRepository.findAll();
    }

    @PostMapping
    public Educacao salvar(@RequestBody Educacao educacao) {
        return educacaoRepository.save(educacao);
    }

    @GetMapping("/{id}")
    public Educacao buscar(@PathVariable Long id) {
        return educacaoRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Educacao atualizar(@PathVariable Long id, @RequestBody Educacao novaEducacao) {
        return educacaoRepository.findById(id).map(educacao -> {
            educacao.setInstituicao(novaEducacao.getInstituicao());
            educacao.setCurso(novaEducacao.getCurso());
            educacao.setNivel(novaEducacao.getNivel());
            educacao.setDataInicio(novaEducacao.getDataInicio());
            educacao.setDataFim(novaEducacao.getDataFim());
            educacao.setPessoa(novaEducacao.getPessoa());
            return educacaoRepository.save(educacao);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        educacaoRepository.deleteById(id);
    }

}
