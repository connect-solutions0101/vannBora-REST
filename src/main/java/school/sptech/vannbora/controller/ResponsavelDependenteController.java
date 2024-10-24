package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteRequestDto;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteResponseDto;
import school.sptech.vannbora.entidade.ResponsavelDependente;
import school.sptech.vannbora.mapper.ResponsavelDependenteMapper;
import school.sptech.vannbora.service.ResponsavelDependenteService;

import java.util.List;

@RestController
@RequestMapping("/responsaveis-dependentes")
@RequiredArgsConstructor
public class ResponsavelDependenteController {
    
    private final ResponsavelDependenteService responsavelDependenteService;

    @Operation(summary = "Listar responsáveis e dependentes", description = "Método lista relaciomentos entre responsáveis e dependentes do usuário.", tags = "Responsavéis Dependentes Controller")
    @GetMapping
    public ResponseEntity<List<ResponsavelDependenteResponseDto>> listar(){
        List<ResponsavelDependente> lista = responsavelDependenteService.listar();

        if(lista.isEmpty()) return ResponseEntity.noContent().build();

        List<ResponsavelDependenteResponseDto> responseDtoList = lista.stream()
            .map(ResponsavelDependenteMapper::toResponseDto)
            .toList();

        return ResponseEntity.ok(responseDtoList);
    }

    @Operation(summary = "Cadastrar responsáveis e dependentes", description = "Método cadastra relaciomentos entre responsáveis e dependentes do usuário.", tags = "Responsavéis Dependentes Controller")
    @PostMapping
    public ResponseEntity<ResponsavelDependenteResponseDto> cadastrar(@Valid @RequestBody ResponsavelDependenteRequestDto responsavelDependente){
        ResponsavelDependente responsavelDependenteEntity = ResponsavelDependenteMapper.toEntity(responsavelDependente);

        return ResponseEntity.created(null).body(ResponsavelDependenteMapper.toResponseDto(responsavelDependenteService.cadastrar(responsavelDependenteEntity, responsavelDependente.responsavelId(), responsavelDependente.dependenteId())));
    }

    @Operation(summary = "Atualizar responsáveis e dependentes", description = "Método atualiza relaciomentos entre responsáveis e dependentes do usuário.", tags = "Responsavéis Dependentes Controller")
    @PutMapping("/{idResponsavel}/{idDependente}")
    public ResponseEntity<ResponsavelDependenteResponseDto> atualizar(
        @PathVariable int idResponsavel,
        @PathVariable int idDependente,
        @Valid @RequestBody ResponsavelDependenteRequestDto responsavelDependente
        ){
        ResponsavelDependente responsavelDependenteEntity = ResponsavelDependenteMapper.toEntity(responsavelDependente);

        return ResponseEntity.ok(ResponsavelDependenteMapper.toResponseDto(responsavelDependenteService.atualizar(responsavelDependenteEntity, idResponsavel, idDependente)));
    }

}
