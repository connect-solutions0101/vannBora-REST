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
import school.sptech.vannbora.dto.fatura.FaturaRequestDto;
import school.sptech.vannbora.dto.fatura.FaturaResponseDto;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.mapper.FaturaMapper;
import school.sptech.vannbora.service.FaturaService;

import java.util.List;

@RestController
@RequestMapping("/faturas")
@RequiredArgsConstructor
public class FaturaController {
    
    private final FaturaService faturaService;

    @Operation(summary = "Listar Faturas", description = "Método lista as faturas do usuário.", tags = "Fatura Controller")
    @GetMapping
    public ResponseEntity<List<FaturaResponseDto>> listarFaturas() {
        List<Fatura> faturas = faturaService.listar();

        if (faturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(faturas.stream().map(
            FaturaMapper::toFaturaResponseDto
        ).toList());
    }

    @Operation(summary = "Listar Faturas Por Dependente", description = "Método lista as faturas por dependentes do usuário.", tags = "Fatura Controller")
    @GetMapping("/dependente/{id}")
    public ResponseEntity<List<FaturaResponseDto>> listarFaturasPorDependente(@PathVariable int id) {
        List<Fatura> faturas = faturaService.listarPorIdDependente(id);

        if (faturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(faturas.stream().map(
            FaturaMapper::toFaturaResponseDto
        ).toList());
    }

    @Operation(summary = "Listar Faturas Por Responsável", description = "Método lista as faturas do usuário por responsável.", tags = "Fatura Controller")
    @GetMapping("/responsavel/{id}")
    public ResponseEntity<List<FaturaResponseDto>> listarFaturasPorResponsavel(@PathVariable int id) {
        List<Fatura> faturas = faturaService.listarPorIdResponsavel(id);

        if (faturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(faturas.stream().map(
            FaturaMapper::toFaturaResponseDto
        ).toList());
    }

    @Operation(summary = "Buscar Faturas por Id", description = "Método lista as faturas do usuário por Id.", tags = "Fatura Controller")
    @GetMapping("/{id}")
    public ResponseEntity<FaturaResponseDto> buscarFaturaPorId(@PathVariable int id) {
        Fatura fatura = faturaService.buscarPorId(id);

        if (fatura == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(FaturaMapper.toFaturaResponseDto(fatura));
    }

    @Operation(summary = "Salvar Faturas", description = "Método salva as faturas do usuário.", tags = "Fatura Controller")
    @PostMapping
    public ResponseEntity<FaturaResponseDto> salvar(@Valid @RequestBody FaturaRequestDto fatura) {
        Fatura novaFatura = FaturaMapper.toFatura(fatura);

        return ResponseEntity.created(null).body(
            FaturaMapper.toFaturaResponseDto(
                faturaService.salvar(
                    novaFatura,
                    fatura.responsavelId(),
                    fatura.dependenteId()
                )
            )
        );
    }

    @Operation(summary = "Atualizar Faturas", description = "Método atualiza as faturas do usuário.", tags = "Fatura Controller")
    @PutMapping("/{id}")
    public ResponseEntity<FaturaResponseDto> atualizar(@PathVariable int id,@Valid @RequestBody FaturaRequestDto fatura) {
        Fatura faturaAtualizada = FaturaMapper.toFatura(fatura);

        return ResponseEntity.ok(
            FaturaMapper.toFaturaResponseDto(
                faturaService.atualizar(
                    faturaAtualizada,
                    fatura.responsavelId(),
                    fatura.dependenteId()
                )
            )
        );
    }

    @Operation(summary = "Deletar Faturas", description = "Método deleta as faturas do usuário.", tags = "Fatura Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        faturaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
