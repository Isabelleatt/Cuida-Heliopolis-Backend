package com.insper.cuida_heliopolis.verificado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificadoRepository  extends JpaRepository<Verificado, Integer> {

    Verificado findByCuidadorId(Integer id);

}
