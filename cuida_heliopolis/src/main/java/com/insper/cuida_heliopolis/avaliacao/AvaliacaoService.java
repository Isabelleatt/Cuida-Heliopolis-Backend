package com.insper.cuida_heliopolis.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AvaliacaoService {
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    // salva uma avaliação criada anteriormente.
    public Avaliacao salvaAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }
    // Procura por todas as avaliações de uma cuidadora.
    public List<AvaliaCuidadora> buscaAvaliacaoCuidadora(Integer id) {
        return avaliacaoRepository.findByAvaliado_id(id);
    }
    // Procura por todas as avaliacoes de uma mâe.
    public List<AvaliaResponsavel> buscaAvaliacaoResponsavel(Integer id) {
        return avaliacaoRepository.findByAvaliador_id(id);
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
    public void deletaAvaliacao(Integer id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id).get();
        if (avaliacao != null) {
            avaliacaoRepository.delete(avaliacao);
        }
    }
}
