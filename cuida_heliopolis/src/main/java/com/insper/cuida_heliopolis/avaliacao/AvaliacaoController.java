package com.insper.cuida_heliopolis.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/cuidadora/{id}")
    public List<AvaliaCuidadora> listaMensagens(@PathVariable Integer id) {
        return avaliacaoService.buscaAvaliacaoCuidadora(id);
    }
    @PostMapping("/cuidadora")
    public Avaliacao salvarAvaliacaoCuidadora(@RequestBody AvaliaCuidadora avaliacao) {
        return avaliacaoService.salvaAvaliacao(avaliacao);
    }
    @PostMapping("responsavel")
    public Avaliacao salvarAvaliacaoResponsavel(@RequestBody AvaliaResponsavel avaliacao) {
        return avaliacaoService.salvaAvaliacao(avaliacao);
    }

    @DeleteMapping("/{id}")
    public void deletaAvaliacao(@PathVariable Integer id) {
        avaliacaoService.deletaAvaliacao(id);
    }
}
