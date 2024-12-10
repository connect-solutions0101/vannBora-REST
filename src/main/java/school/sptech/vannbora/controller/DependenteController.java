package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.dto.dependente.DependenteEscolaResponsaveisResponseDto;
import school.sptech.vannbora.dto.dependente.DependenteRequestDto;
import school.sptech.vannbora.dto.dependente.DependenteResponsavelEnderecoFaturaRequestDto;
import school.sptech.vannbora.dto.dependente.DependenteResponsavelEnderecoFaturaResponseDto;
import school.sptech.vannbora.dto.dependente.DependenteResponseDto;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.entidade.Responsavel;
import school.sptech.vannbora.mapper.DependenteMapper;
import school.sptech.vannbora.mapper.EnderecoMapper;
import school.sptech.vannbora.mapper.FaturaMapper;
import school.sptech.vannbora.mapper.ResponsavelMapper;
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
    @Operation(summary = "Listar tudo", description = "Método lista todos os dados do dependente inseridos no banco de dados.", tags = "Dependentes Controller")
    @GetMapping("/full/{id}")
    public ResponseEntity<List<DependenteEscolaResponsaveisResponseDto>> listarFull(@PathVariable int id, @RequestParam(required = false) String nome) {
        List<Dependente> dependentes = dependenteService.listarFull(id, nome);

        if (dependentes.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(dependentes.stream().map(DependenteMapper::toDependenteEscolaResponseDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Buscar Por Id", description = "Método busca o dependente pelo id inserido pelo usuário no banco de dados.", tags = "Dependentes Controller")
    @GetMapping("/{id}")
    public ResponseEntity<DependenteResponseDto> buscarPorId(@PathVariable int id) {
        Dependente dependente = dependenteService.buscarPorId(id);
        return ResponseEntity.ok(DependenteMapper.toDependenteResponseDto(dependente));
    }
    @Operation(summary = "Buscar todos os Dependentes por Id", description = "Método busca todos os dependentes por Id no banco de dados .", tags = "Dependentes Controller")
    @GetMapping("/fullPorId/{id}")
    public ResponseEntity<DependenteResponsavelEnderecoFaturaResponseDto> buscarFullPorId(@PathVariable int id) {
        Dependente dependente = dependenteService.buscarPorId(id);
        return ResponseEntity.ok(DependenteMapper.toDependenteResponsavelEnderecoFaturaResponseDto(dependente));
    }
    
    @Operation(summary = "Salvar Dependentes", description = "Método salva o dependente inserido pelo usuário no banco de dados.", tags = "Dependentes Controller")
    @PostMapping
    public ResponseEntity<DependenteResponseDto> salvar(@RequestBody @Valid DependenteRequestDto dependente) {
        Dependente dependenteEntity = DependenteMapper.toDependente(dependente);
        dependenteEntity = dependenteService.salvar(dependenteEntity, dependente.escolaId(), dependente.proprietarioServicoId());
        return ResponseEntity.created(null).body(DependenteMapper.toDependenteResponseDto(dependenteEntity));
    }

    @PostMapping("/full/{id}")
    public ResponseEntity<DependenteResponseDto> salvarFull(@RequestBody @Valid DependenteResponsavelEnderecoFaturaRequestDto dependente, @PathVariable int id) {
        Dependente dependenteEntity = DependenteMapper.toDependente(dependente);
        Responsavel responsavelFinanceiro = ResponsavelMapper.toEntity(dependente.responsaveis().stream().filter(r -> r.tipoResponsavel().equals("FINANCEIRO")).findFirst().get().responsavel());
        Responsavel responsavelSecundario = ResponsavelMapper.toEntity(dependente.responsaveis().stream().filter(r -> r.tipoResponsavel().equals("PADRAO") && r.responsavel().cpf() != null).findFirst().map(r -> r.responsavel()).orElse(null));
        Endereco endereco = EnderecoMapper.toEndereco(dependente.responsaveis().stream().filter(r -> r.tipoResponsavel().equals("FINANCEIRO")).findFirst().get().responsavel().endereco());
        Fatura fatura = FaturaMapper.toFatura(dependente.fatura());

        dependenteEntity = dependenteService.salvarFull(id, dependenteEntity, dependente.escolaId(), responsavelFinanceiro, responsavelSecundario, endereco, fatura);

        return ResponseEntity.created(null).body(DependenteMapper.toDependenteResponseDto(dependenteEntity));
    }

    @Operation(summary = "Atualizar Dependentes Por Id", description = "Método atualiza o dependente inserido pelo usuário no banco de dados.", tags = "Dependentes Controller")
    @PutMapping("/{id}")
    public ResponseEntity<DependenteResponseDto> atualizar(@PathVariable int id, @RequestBody @Valid DependenteRequestDto dependente) {
        Dependente dependenteEntity = DependenteMapper.toDependente(dependente);
        dependenteEntity = dependenteService.atualizar(id, dependenteEntity, dependente.escolaId(), dependente.proprietarioServicoId());
        return ResponseEntity.ok(DependenteMapper.toDependenteResponseDto(dependenteEntity));
    }
    @Operation(summary = "Atualizar todos os Dependentes", description = "Método lista o dependentes inserido pelo usuário no banco de dados.", tags = "Dependentes Controller")
    @PutMapping("/full/{id}")
    public ResponseEntity<DependenteResponsavelEnderecoFaturaResponseDto> atualizarFull(@PathVariable int id, @RequestBody @Valid DependenteResponsavelEnderecoFaturaRequestDto dependente) {
        Dependente dependenteEntity = DependenteMapper.toDependente(dependente);
        dependenteEntity = dependenteService.atualizarFull(id, dependenteEntity, dependente.escolaId(), dependente.fatura());
        return ResponseEntity.ok(DependenteMapper.toDependenteResponsavelEnderecoFaturaResponseDto(dependenteEntity));
    }

    @Operation(summary = "Deletar Dependentes Por ID", description = "Método deleta o dependente inserido pelo usuário no banco..", tags = "Dependentes Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        dependenteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
} 
