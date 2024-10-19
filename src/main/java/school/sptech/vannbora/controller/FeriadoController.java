package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.vannbora.dto.feriado.FeriadoDto;
import school.sptech.vannbora.service.FeriadoService;

import java.util.List;

@RestController
@RequestMapping("/feriados")
@RequiredArgsConstructor
public class FeriadoController {

    private final FeriadoService service;

    @Operation(summary = "Buscar feriados por ano", description = "Método retorna todos os feriados de determinado ano por ordem alfabética.", tags = "Feriado Controller")
    @GetMapping("/{ano}")
    public ResponseEntity<List<FeriadoDto>> buscarFeriadosPorAno(@PathVariable @NotNull @Valid int ano){
        return ResponseEntity.ok(service.buscarFeriadosPorAno(ano, true));
    }

    @Operation(summary = "Buscar feriado hoje", description = "Método retorna se a data atual é feriado.", tags = "Feriado Controller")
    @GetMapping("/hoje")
    public ResponseEntity<FeriadoDto> buscarFeriadoHoje(){
        return ResponseEntity.ok(service.buscarFeriadoHoje());
    }
}
