package school.sptech.vannbora.service;

import org.springframework.beans.factory.annotation.Autowired;

import school.sptech.vannbora.entidade.Exemplo;
import school.sptech.vannbora.repository.ExemploRepository;

import java.util.List;

public class ExemploService {
    
    @Autowired
    private ExemploRepository repository;

    public List<Exemplo> listar() {
        return repository.findAll();
    }
}
