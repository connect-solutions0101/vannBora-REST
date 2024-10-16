package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.dto.responsavel.ResponsavelRequestDto;
import school.sptech.vannbora.dto.responsavel.ResponsavelResponseDto;
import school.sptech.vannbora.entidade.Responsavel;
import school.sptech.vannbora.mapper.ResponsavelMapper;
import school.sptech.vannbora.service.ResponsavelService;

import java.util.List;

@RestController
@RequestMapping("/responsaveis")
@RequiredArgsConstructor
public class ResponsavelController {
    
    private final ResponsavelService service;

    @Operation(summary = "Listar Escola ", description = "Método lista a escola no banco de dados.", tags = "Responsável Controller")
    @GetMapping
    public ResponseEntity<List<ResponsavelResponseDto>> listar(){
        List<Responsavel> lista = service.listar();

        if(lista.isEmpty()) return ResponseEntity.noContent().build();

        List<ResponsavelResponseDto> responseDtoList = lista.stream()
            .map(ResponsavelMapper::toResponseDto)
            .toList();

        return ResponseEntity.ok(responseDtoList);
    }

    @Operation(summary = "Cadastrar Escola ", description = "Método cadastra o responsável inserido no banco de dados.", tags = "Responsável Controller")
    @PostMapping
    public ResponseEntity<ResponsavelResponseDto> cadastrar(@Valid @RequestBody ResponsavelRequestDto responsavel){
        Responsavel responsavelEntity = ResponsavelMapper.toEntity(responsavel);

        return ResponseEntity.created(null).body(ResponsavelMapper.toResponseDto(service.cadastrar(responsavelEntity, responsavel.enderecoId(), responsavel.proprietarioServicoId())));
    }

    @Operation(summary = "Atualizar Escola ", description = "Método atualiza a escola pelo id inserido no banco de dados.", tags = "Responsável Controller")
    @PutMapping("/{id}")
    public ResponseEntity<ResponsavelResponseDto> atualizar(@PathVariable int id, @Valid @RequestBody ResponsavelRequestDto responsavel){
        Responsavel responsavelEntity = ResponsavelMapper.toEntity(responsavel);

        return ResponseEntity.ok(ResponsavelMapper.toResponseDto(service.atualizar(id, responsavelEntity, responsavel.enderecoId(), responsavel.proprietarioServicoId())));
    }
    @Operation(summary = "Deletar Escola ", description = "Método deleta a escola pelo id inserido no banco de dados.", tags = "Responsável Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}
