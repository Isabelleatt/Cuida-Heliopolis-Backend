package com.insper.cuida_heliopolis.avaliacao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoCuidadoraRepository extends JpaRepository<AvaliaCuidadora, Integer> {
    List<AvaliaCuidadora> findByAvaliadoId(Integer id);
}
