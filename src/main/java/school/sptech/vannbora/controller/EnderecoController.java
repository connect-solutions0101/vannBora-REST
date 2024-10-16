package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Listar Endereço", description = "Método uma lista de endereços ao .", tags = "Endereço Controller")
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

    @Operation(summary = "Buscar o Endereço por Id", description = "Método busca o endereço inserido pelo usuário no banco de dados através do Id.", tags = "Endereço Controller")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> buscarPorId(@PathVariable int id){
        return ResponseEntity.status(200).body(EnderecoMapper.toEnderecoResponseDto(service.buscarPorId(id)));
    }

    @Operation(summary = "Buscar o Endereço por CEP ", description = "Método busca o CEP inserido pelo usuário no banco de dados.", tags = "Endereço Controller")
    @GetMapping("/cep")
    public ResponseEntity<EnderecoResponseDto> buscarPorCep(@RequestParam String cep){
        return ResponseEntity.status(200).body(EnderecoMapper.toEnderecoResponseDto(service.buscarPorCep(cep)));
    }

    @Operation(summary = "Cadastrar Endereço", description = "Método cadastra o endereço inserido pelo usuário no banco de dados.", tags = "Endereço Controller")
    @PostMapping
    public ResponseEntity<EnderecoResponseDto> cadastrar(@RequestBody @Valid EnderecoRequestDto endereco){
        Endereco novoEndereco = EnderecoMapper.toEndereco(endereco);
        return ResponseEntity.status(201).body(EnderecoMapper.toEnderecoResponseDto(service.cadastrar(novoEndereco)));
    }

    @Operation(summary = "Atualizar Endereço", description = "Método atualiza o endereço inserido pelo usuário no banco.", tags = "Endereço Controller")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResponseDto> atualizar(@PathVariable int id, @RequestBody @Valid EnderecoRequestDto endereco){
        Endereco enderecoEditado = EnderecoMapper.toEndereco(endereco);
        return ResponseEntity.status(200).body(EnderecoMapper.toEnderecoResponseDto(service.atualizar(id, enderecoEditado)));
    }
    @Operation(summary = "Deletar Endereço", description = "Método atualiza o endereço inserido pelo usuário no banco.", tags = "Endereço Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
}
