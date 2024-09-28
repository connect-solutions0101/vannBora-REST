package school.sptech.vannbora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.service.ProprietarioServicoService;

@RestController
@RequestMapping("/proprietarios-servicos")
public class ProprietarioServicoController {
    
    @Autowired
    private ProprietarioServicoService service;

    @GetMapping
    public ResponseEntity<List<ProprietarioServico>> listar(){
        List<ProprietarioServico> lista = service.listar();

        if(lista.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioServico> buscarPorId(@RequestParam int id){
        return ResponseEntity.status(200).body(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProprietarioServico> salvar(@RequestParam ProprietarioServico proprietarioServico){
        return ResponseEntity.status(201).body(service.cadastrar(proprietarioServico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProprietarioServico> atualizar(@RequestParam int id, @RequestParam ProprietarioServico proprietarioServico){
        return ResponseEntity.status(200).body(service.atualizar(id, proprietarioServico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@RequestParam int id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
}

