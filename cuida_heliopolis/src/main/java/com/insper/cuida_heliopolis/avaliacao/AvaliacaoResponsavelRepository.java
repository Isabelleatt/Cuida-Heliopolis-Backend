package com.insper.cuida_heliopolis.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoResponsavelRepository extends JpaRepository<AvaliaResponsavel, Integer> {
    List<AvaliaResponsavel> findByAvaliadoId(Integer id);
}
