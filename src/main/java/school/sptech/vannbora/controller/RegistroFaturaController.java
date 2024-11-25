package school.sptech.vannbora.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.dto.registroFatura.RegistroFaturaRequestDto;
import school.sptech.vannbora.dto.registroFatura.RegistroFaturaResponseDto;
import school.sptech.vannbora.mapper.RegistroFaturaMapper;
import school.sptech.vannbora.service.RegistroFaturaService;

@RestController
@RequestMapping("/registros-faturas")
@RequiredArgsConstructor
public class RegistroFaturaController {
    
    private final RegistroFaturaService registroFaturaService;

    @PostMapping
    public ResponseEntity<RegistroFaturaResponseDto> salvar(@RequestBody RegistroFaturaRequestDto registroFatura) {
        return ResponseEntity.created(null).body(RegistroFaturaMapper.toRegistroFaturaResponseDto(
            registroFaturaService.salvar(RegistroFaturaMapper.toRegistroFatura(registroFatura))
        ));
    }

    @GetMapping("/{faturaId}")
    public ResponseEntity<List<RegistroFaturaResponseDto>> listar(@PathVariable int faturaId) {
        return ResponseEntity.ok(registroFaturaService.listarPorFaturaId(faturaId).stream().map(
            RegistroFaturaMapper::toRegistroFaturaResponseDto
        ).toList());
    }
}
