package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.vannbora.dto.clima.ClimaDto;
import school.sptech.vannbora.dto.clima.ClimaApiExternaDto.Results.Forecast;
import school.sptech.vannbora.service.ClimaService;

import java.util.List;

@RestController
@RequestMapping("/climas")
public class ClimaController {

    @Autowired
    private ClimaService climaService;
    @Operation(summary = "Buscar Clima", description = "Método retorna todos os dados de clima ao usuário.", tags = "Clima Controller")
    @GetMapping
    public ResponseEntity<ClimaDto> buscarClima(@RequestParam String city) {        
        return ResponseEntity.status(200).body(climaService.buscarClima(city));
    }

    @Operation(summary = "Buscar Próximos Climas", description = "Método retorna todos os dados de clima das próximas semanas ao usuário.", tags = "Clima Controller")
    @GetMapping("/proximos")
    public ResponseEntity<List<Forecast>> buscarProximosClima(@RequestParam String city, @RequestParam(required = false, defaultValue = "false") boolean sort) {
        List<Forecast> lista = climaService.buscarProximosClima(city, sort);

        if(lista.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(lista);
    }
    
}
