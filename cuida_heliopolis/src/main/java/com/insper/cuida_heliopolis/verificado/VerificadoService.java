package com.insper.cuida_heliopolis.verificado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class VerificadoService {

    @Autowired
    private VerificadoRepository verificadoRepository;

    public Boolean checaValidade(Integer id) {
        Verificado verificado = verificadoRepository.findByCuidadorId(id);
        if (verificado == null) {
            return false;
        }
        
        if (verificado.getStatus() == VerificadoStatus.EXPIRADO){
            return false;
        }

        long diferenca = ChronoUnit.YEARS.between(verificado.getDataAprovacao(), LocalDateTime.now());

        if(diferenca >= 1){
            verificado.setStatus(VerificadoStatus.EXPIRADO);
            return false;
        }

        return true;

    }

        
    public void alteraStatus(Integer id) {
        Verificado verificado = verificadoRepository.findByCuidadorId(id);
        if (verificado != null) {
            
            if (verificado.getStatus() == VerificadoStatus.ATIVO){
                verificado.setStatus(VerificadoStatus.EXPIRADO);
            }
            else if (verificado.getStatus() == VerificadoStatus.EXPIRADO){
                verificado.setStatus(VerificadoStatus.ATIVO);
            }
        }
    }
        
}
    

