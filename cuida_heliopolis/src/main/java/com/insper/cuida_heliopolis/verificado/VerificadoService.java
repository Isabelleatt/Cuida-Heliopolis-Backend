package com.insper.cuida_heliopolis.verificado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insper.cuida_heliopolis.usuario.Cuidador;
import com.insper.cuida_heliopolis.usuario.Membro;
import com.insper.cuida_heliopolis.usuario.Usuario;
import com.insper.cuida_heliopolis.usuario.UsuarioRepository;
import com.insper.cuida_heliopolis.verificado.dto.VerificadoReturnDTO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class VerificadoService {

    @Autowired
    private VerificadoRepository verificadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean checaValidade(String email) {
        Verificado verificado = verificadoRepository.findByCuidadorEmail(email);
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

        
    public void alteraStatus(String email) {
        Verificado verificado = verificadoRepository.findByCuidadorEmail(email);
        if (verificado != null) {
            
            if (verificado.getStatus() == VerificadoStatus.ATIVO){
                verificado.setStatus(VerificadoStatus.EXPIRADO);
            }
            else if (verificado.getStatus() == VerificadoStatus.EXPIRADO){
                verificado.setStatus(VerificadoStatus.ATIVO);
            }
            verificadoRepository.save(verificado);
        }
    }

    public VerificadoReturnDTO adicionarVerificado (String email_cuidador, String email_aprovador) {
        Verificado v = new Verificado();
        Membro m = (Membro) usuarioRepository.findByEmail(email_aprovador).get();
        Cuidador c = (Cuidador) usuarioRepository.findByEmail(email_cuidador).get();
        if (m == null || c == null) {return null;}
        v.setAprovador_email(email_aprovador);
        v.setCuidador_email(email_cuidador);
        v.setCuidador(c);
        v.setAprovador(m);
        v.setDataAprovacao(LocalDateTime.now());
        v.setStatus(VerificadoStatus.ATIVO);
        verificadoRepository.save(v);
        return VerificadoReturnDTO.convert(v);

    }
        
}
    

