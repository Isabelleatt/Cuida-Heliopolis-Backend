package com.insper.cuida_heliopolis.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoCuidadoraReturnDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoCuidadoraSaveDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoResponsavelReturnDTO;
import com.insper.cuida_heliopolis.avaliacao.dto.AvaliacaoResponsavelSaveDTO;

import java.util.List;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping("/cuidadora/{email}")
    public List<Avaliacao> listaAvaliacoesCuidadora(@PathVariable String email) {
        return avaliacaoService.buscaAvaliacoes(email);
    }
    @GetMapping("/responsavel/{email}")
    public List<Avaliacao> listaAvaliacoesResponsavel(@PathVariable String email) {
        return avaliacaoService.buscaAvaliacoes(email);
    }
    @PostMapping("/cuidadora/{email}")
    public AvaliacaoCuidadoraReturnDTO salvarAvaliacaoCuidadora(@RequestBody AvaliacaoCuidadoraSaveDTO avaliacao, @PathVariable String email) {
        return avaliacaoService.salvaAvaliacaoCuidadora(avaliacao, email);
    }
    @PostMapping("/responsavel/{email}")
    public AvaliacaoResponsavelReturnDTO salvarAvaliacaoResponsavel(@RequestBody AvaliacaoResponsavelSaveDTO avaliacao, @PathVariable String email) {
        return avaliacaoService.salvaAvaliacaoResponsavel(avaliacao, email);
    }

    @DeleteMapping("/cuidadora/{id}")
    public void deletaAvaliacaoCuidadora(@PathVariable Integer id) {
        avaliacaoService.deletaAvaliacao(id);
    }
    @DeleteMapping("/responsavel/{id}")
    public void deletaAvaliacaoResponsavel(@PathVariable Integer id) {
        avaliacaoService.deletaAvaliacao(id);
    }
}
