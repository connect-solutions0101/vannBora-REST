package school.sptech.vannbora.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.endereco.EnderecoRequestDto;
import school.sptech.vannbora.dto.endereco.EnderecoResponseDto;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.mapper.EnderecoMapper;
import school.sptech.vannbora.service.EnderecoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public ResponseEntity<List<EnderecoResponseDto>> listar(){
        List<Endereco> lista = service.listar();

        if(lista.isEmpty()) return ResponseEntity.status(204).build();

        List<EnderecoResponseDto> dtoLista = new ArrayList<>();
        for(Endereco endereco : lista){
            dtoLista.add(EnderecoMapper.toEnderecoResponseDto(endereco));
        }

        return ResponseEntity.status(200).body(dtoLista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> buscarPorId(@PathVariable int id){
        return ResponseEntity.status(200).body(EnderecoMapper.toEnderecoResponseDto(service.buscarPorId(id)));
    }

    @GetMapping("/cep")
    public ResponseEntity<EnderecoResponseDto> buscarPorCep(@RequestParam String cep){
        return ResponseEntity.status(200).body(EnderecoMapper.toEnderecoResponseDto(service.buscarPorCep(cep)));
    }

    @PostMapping
    public ResponseEntity<EnderecoResponseDto> cadastrar(@RequestBody @Valid EnderecoRequestDto endereco){
        Endereco novoEndereco = EnderecoMapper.toEndereco(endereco);
        return ResponseEntity.status(201).body(EnderecoMapper.toEnderecoResponseDto(service.cadastrar(novoEndereco)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizar(@PathVariable int id, @RequestBody @Valid EnderecoRequestDto endereco){
        Endereco enderecoEditado = EnderecoMapper.toEnderecoAtualizar(id, endereco);
        return ResponseEntity.status(200).body(EnderecoMapper.toEnderecoResponseDto(service.atualizar(id, enderecoEditado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
