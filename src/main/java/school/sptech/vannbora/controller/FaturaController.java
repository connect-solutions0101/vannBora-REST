package school.sptech.vannbora.controller;

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

    @GetMapping("/{id}")
    public ResponseEntity<FaturaResponseDto> buscarFaturaPorId(@PathVariable int id) {
        Fatura fatura = faturaService.buscarPorId(id);

        if (fatura == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(FaturaMapper.toFaturaResponseDto(fatura));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        faturaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
