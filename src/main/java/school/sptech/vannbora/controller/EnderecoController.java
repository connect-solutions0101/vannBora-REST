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
        List<Endereco> lista = service.listar();

        if(lista.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable int id){
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }

    @GetMapping("/cep")
    public ResponseEntity<Endereco> buscarPorCep(@RequestParam String cep){
        return ResponseEntity.status(200).body(service.buscarPorCep(cep));
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(@RequestBody Endereco endereco){
        return ResponseEntity.status(201).body(service.cadastrar(endereco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(@PathVariable int id, @RequestBody Endereco endereco){
        return ResponseEntity.status(200).body(service.atualizar(id, endereco));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
