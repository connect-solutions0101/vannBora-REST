package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.dto.dependente.DependenteEscolaResponsaveisResponseDto;
import school.sptech.vannbora.dto.dependente.DependenteRequestDto;
import school.sptech.vannbora.dto.dependente.DependenteResponseDto;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.mapper.DependenteMapper;
import school.sptech.vannbora.service.DependenteService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/dependentes")
@RequiredArgsConstructor
public class DependenteController {
    
    private final DependenteService dependenteService;
    @Operation(summary = "Listar Dependentes", description = "Método lista o dependentes inserido pelo usuário no banco de dados.", tags = "Dependentes Controller")
    @GetMapping
    public ResponseEntity<List<DependenteResponseDto>> listar() {
        List<Dependente> dependentes = dependenteService.listar();

        if (dependentes.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(dependentes.stream().map(DependenteMapper::toDependenteResponseDto).collect(Collectors.toList()));
    }
    @Operation(summary = "Listar Dependentes, Escolas e Responsáveis.", description = "Método lista o dependentes, escolas e responsáveis.", tags = "Dependentes Controller")
    @GetMapping("/full")
    public ResponseEntity<List<DependenteEscolaResponsaveisResponseDto>> listarFull() {
        List<Dependente> dependentes = dependenteService.listarFull();

        if (dependentes.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(dependentes.stream().map(DependenteMapper::toDependenteEscolaResponseDto).collect(Collectors.toList()));
    }
    
    @Operation(summary = "Buscar Por Id", description = "Método busca o dependente pelo id inserido pelo usuário no banco de dados.", tags = "Dependentes Controller")
    @GetMapping("/{id}")
    public ResponseEntity<DependenteResponseDto> buscarPorId(@PathVariable int id) {
        Dependente dependente = dependenteService.buscarPorId(id);
        return ResponseEntity.ok(DependenteMapper.toDependenteResponseDto(dependente));
    }
    @Operation(summary = "Salvar Dependentes", description = "Método salva o dependente inserido pelo usuário no banco..", tags = "Dependentes Controller")
    @PostMapping
    public ResponseEntity<DependenteResponseDto> salvar(@RequestBody @Valid DependenteRequestDto dependente) {
        Dependente dependenteEntity = DependenteMapper.toDependente(dependente);
        dependenteEntity = dependenteService.salvar(dependenteEntity, dependente.escolaId(), dependente.proprietarioServicoId());
        return ResponseEntity.created(null).body(DependenteMapper.toDependenteResponseDto(dependenteEntity));
    }
    @Operation(summary = "Atualizar Dependentes Por Id", description = "Método atualiza o dependente inserido pelo usuário no banco..", tags = "Dependentes Controller")
    @PutMapping("/{id}")
    public ResponseEntity<DependenteResponseDto> atualizar(@PathVariable int id, @RequestBody @Valid DependenteRequestDto dependente) {
        Dependente dependenteEntity = DependenteMapper.toDependente(dependente);
        dependenteEntity = dependenteService.atualizar(id, dependenteEntity, dependente.escolaId(), dependente.proprietarioServicoId());
        return ResponseEntity.ok(DependenteMapper.toDependenteResponseDto(dependenteEntity));
    }
    @Operation(summary = "Deletar Dependentes Por ID", description = "Método deleta o dependente inserido pelo usuário no banco..", tags = "Dependentes Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        dependenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 
