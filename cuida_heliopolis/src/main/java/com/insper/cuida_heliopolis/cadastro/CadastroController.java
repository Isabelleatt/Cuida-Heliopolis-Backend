package com.insper.cuida_heliopolis.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "ap1/v1/registration")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;
    
    public String cadastrar(@RequestBody CadastroRequest requisicao) {
        return cadastroService.cadastrar(requisicao);
    }
}
