package school.sptech.vannbora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.service.EnderecoService;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<List<Endereco>> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable int id){
        return service.buscarPorId(id);
    }

    @GetMapping("/cep")
    public ResponseEntity<Endereco> buscarPorCep(@RequestParam String cep){
        return service.buscarPorCep(cep);
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco){
        return service.cadastrar(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable int id, @RequestBody Endereco endereco){
        return service.atualizar(id, endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        return service.deletar(id);
    }
}
