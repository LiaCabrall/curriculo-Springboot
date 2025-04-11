package com.lianacabral.demo.controller;

import com.lianacabral.demo.model.Habilidade;
import com.lianacabral.demo.repository.HabilidadeRepository;
import com.lianacabral.demo.repository.PessoaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habilidades")
public class HabilidadeController {

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public List<Habilidade> listar() {
        return habilidadeRepository.findAll();
    }

    @PostMapping
    public Habilidade salvar(@RequestBody Habilidade habilidade) {
        return habilidadeRepository.save(habilidade);
    }

    @GetMapping("/{id}")
    public Habilidade buscar(@PathVariable Long id) {
        return habilidadeRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Habilidade atualizar(@PathVariable Long id, @RequestBody Habilidade novaHabilidade) {
        return habilidadeRepository.findById(id).map(habilidade -> {
            habilidade.setNome(novaHabilidade.getNome());
            habilidade.setNivel(novaHabilidade.getNivel());

            // Aqui garantimos que a Pessoa está vindo do banco
            if (novaHabilidade.getPessoa() != null && novaHabilidade.getPessoa().getId() != null) {
                pessoaRepository.findById(novaHabilidade.getPessoa().getId())
                        .ifPresent(habilidade::setPessoa);
            }

            return habilidadeRepository.save(habilidade);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        habilidadeRepository.deleteById(id);
    }

}
