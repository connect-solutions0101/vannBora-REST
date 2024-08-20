package school.sptech.vannbora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.vannbora.entidade.Exemplo;
import school.sptech.vannbora.service.ExemploService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/exemplo")
public class ExemploController {
    
    @Autowired
    private ExemploService exemploService;

    @GetMapping
    public List<Exemplo> listar() {
        return exemploService.listar();
    }
    
}
