package com.insper.cuida_heliopolis.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    List<AvaliaCuidadora> findByAvaliadoId(Integer id);
    List<AvaliaResponsavel> findByAvaliadorId(Integer id);
}
