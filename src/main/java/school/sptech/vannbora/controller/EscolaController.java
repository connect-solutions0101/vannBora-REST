package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.vannbora.dto.escola.EscolaAlunosResponseDto;
import school.sptech.vannbora.dto.escola.EscolaEditRequestDto;
import school.sptech.vannbora.dto.escola.EscolaRequestDto;
import school.sptech.vannbora.dto.escola.EscolaResponseDto;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.mapper.EscolaMapper;
import school.sptech.vannbora.service.EscolaService;

import java.util.List;

@RestController
@RequestMapping("/escolas")
@RequiredArgsConstructor
public class EscolaController {

    private final EscolaService service;

    @Operation(summary = "Listar Escolas", description = "Método lista o dependente inserido pelo usuário no banco..", tags = "Escola Controller")
    @GetMapping
    public ResponseEntity<List<EscolaResponseDto>> listar(){
        List<Escola> escolas = service.listar();

        if(escolas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(escolas.stream().map(EscolaMapper::toEscolaResponseDto).toList());
    }
    @Operation(summary = "Listar Tudo", description = "Método lista alunos e dependentes.", tags = "Escola Controller")
    @GetMapping("/full/{id}")
    public ResponseEntity<List<EscolaAlunosResponseDto>> listarFull(@PathVariable int id){
        List<EscolaAlunosResponseDto> escolas = service.listarFull(id);

        if(escolas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(escolas);
    }

    @Operation(summary = "Busca Escola Por Id", description = "Método busca a escola pelo id inserido pelo usuário no banco de dados.", tags = "Escola Controller")
    @GetMapping("/{id}")
    public ResponseEntity<EscolaResponseDto> buscarPorId(@PathVariable int id){
        Escola escola = service.buscarPorId(id);
        return ResponseEntity.ok(EscolaMapper.toEscolaResponseDto(escola));
    }

    @Operation(summary = "Cadastrar Escola ", description = "Método cadastra a escola no banco de dados.", tags = "Escola Controller")
    @PostMapping
    public ResponseEntity<EscolaResponseDto> cadastrar(@Valid @RequestBody EscolaRequestDto dto){
        Escola escola = EscolaMapper.toEscola(dto);

        return ResponseEntity.created(null).body(EscolaMapper.toEscolaResponseDto(service.cadastrar(escola, dto.enderecoId(), dto.proprietarioServicoId())));
    }

    @Operation(summary = "Atualizar Escola ", description = "Método atualiza a escola pelo id inserido no banco de dados.", tags = "Escola Controller")
    @PutMapping("/{id}")
    public ResponseEntity<EscolaResponseDto> atualizar(@PathVariable int id, @Valid @RequestBody EscolaEditRequestDto dto){
        Escola escola = EscolaMapper.toEscola(dto);

        Escola escolaAtualizada = service.atualizar(id, escola);

        return ResponseEntity.ok(EscolaMapper.toEscolaResponseDto(escolaAtualizada));
    }

    @Operation(summary = "Deletar Escola Por Id ", description = "Método deleta a escola pelo id inserido no banco de dados.", tags = "Escola Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
