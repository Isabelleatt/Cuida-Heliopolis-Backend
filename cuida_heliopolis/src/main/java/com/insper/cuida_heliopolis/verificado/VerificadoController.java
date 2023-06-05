package com.insper.cuida_heliopolis.verificado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/verificado")
public class VerificadoController {

    @Autowired
    private VerificadoService verificadoService;

    @GetMapping("/{id}")
    public Boolean checaValidade(@PathVariable Integer id) {
        return verificadoService.checaValidade(id);
    }

    @PutMapping("/{id}")
    public void alteraStatus(@PathVariable Integer id) {
        verificadoService.alteraStatus(id);
    }
}
