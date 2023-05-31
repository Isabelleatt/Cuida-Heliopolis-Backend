package com.insper.cuida_heliopolis.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {
    @Autowired
    private AvaliacaoResponsavelRepository avaliacaoRepository;
    @Autowired
    private AvaliacaoCuidadoraRepository avaliacaoCuidadoraRepository;
    // salva uma avaliação criada anteriormente.
    public AvaliaCuidadora salvaAvaliacaoCuidadora(AvaliaCuidadora avaliacao) {
        return avaliacaoCuidadoraRepository.save(avaliacao);
    }
    public AvaliaResponsavel salvaAvaliacaoResponsavel(AvaliaResponsavel avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }
    // Procura por todas as avaliações de uma cuidadora.
    public List<AvaliaCuidadora> buscaAvaliacaoCuidadora(Integer id) {
        return avaliacaoCuidadoraRepository.findByAvaliadoId(id);
    }
    // Procura por todas as avaliacoes de uma mâe.
    public List<AvaliaResponsavel> buscaAvaliacaoResponsavel(Integer id) {
        return avaliacaoRepository.findByAvaliadoId(id);
    }
    // busca por id.
    public Avaliacao buscaAvaliacao(Integer id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).get();
        if (avaliacao != null) {
            return avaliacao;
        }
        return null;
    }
    // deleta usando o id para isso.
    public void deletaAvaliacaoResponsavel(Integer id) {
        AvaliaResponsavel avaliacao = avaliacaoRepository.findById(id).get();
        if (avaliacao != null) {
            avaliacaoRepository.delete(avaliacao);
        }
    }
    public void deletaAvaliacaoCuidadora(Integer id) {
        AvaliaCuidadora avaliacao = avaliacaoCuidadoraRepository.findById(id).get();
        if (avaliacao != null) {
            avaliacaoCuidadoraRepository.delete(avaliacao);
        }
    }
}
