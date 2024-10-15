package school.sptech.vannbora.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/dependente")
@RequiredArgsConstructor
public class DependenteController {
    
    private final DependenteService dependenteService;

    @GetMapping
    public ResponseEntity<List<DependenteResponseDto>> listar() {
        List<Dependente> dependentes = dependenteService.listar();

        if (dependentes.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(dependentes.stream().map(DependenteMapper::toDependenteResponseDto).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DependenteResponseDto> buscarPorId(@PathVariable int id) {
        Dependente dependente = dependenteService.buscarPorId(id);
        return ResponseEntity.ok(DependenteMapper.toDependenteResponseDto(dependente));
    }

    @PostMapping
    public ResponseEntity<DependenteResponseDto> salvar(@RequestBody @Valid DependenteRequestDto dependente) {
        Dependente dependenteEntity = DependenteMapper.toDependente(dependente);
        dependenteEntity = dependenteService.salvar(dependenteEntity);
        return ResponseEntity.created(null).body(DependenteMapper.toDependenteResponseDto(dependenteEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DependenteResponseDto> atualizar(@PathVariable int id, @RequestBody @Valid DependenteRequestDto dependente) {
        Dependente dependenteEntity = DependenteMapper.toDependente(dependente);
        dependenteEntity = dependenteService.atualizar(id, dependenteEntity);
        return ResponseEntity.ok(DependenteMapper.toDependenteResponseDto(dependenteEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        dependenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 
