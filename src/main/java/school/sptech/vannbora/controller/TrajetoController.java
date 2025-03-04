package school.sptech.vannbora.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.dependente.DependenteResponseResumoDto;
import school.sptech.vannbora.dto.trajeto.TrajetoRequestDto;
import school.sptech.vannbora.dto.trajeto.TrajetoResponseDto;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.FilaObj;
import school.sptech.vannbora.entidade.PilhaObj;
import school.sptech.vannbora.entidade.Trajeto;
import school.sptech.vannbora.mapper.TrajetoMapper;
import school.sptech.vannbora.service.DependenteService;
import school.sptech.vannbora.service.TrajetoService;

import java.util.List;

@RestController
@RequestMapping("/trajeto")
@RequiredArgsConstructor
public class TrajetoController {
    private final TrajetoService trajetoService;

    @GetMapping
    public ResponseEntity<List<TrajetoResponseDto>> listar(){
        List<Trajeto> trajetos = trajetoService.listar();

        if (trajetos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(trajetos.stream().map(
                TrajetoMapper::toResponseDto
        ).toList());
    }

    @PostMapping
    public ResponseEntity<TrajetoResponseDto> salvar(@Valid @RequestBody TrajetoRequestDto trajeto){
        Trajeto novoTrajeto = TrajetoMapper.toTrajeto(trajeto);

        return ResponseEntity.created(null).body(
                TrajetoMapper.toResponseDto(
                        trajetoService.salvar(
                                novoTrajeto
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrajetoResponseDto> atualizar(@PathVariable int id, @Valid @RequestBody TrajetoRequestDto trajeto){
        Trajeto trajetoAtualizado = TrajetoMapper.toTrajeto(trajeto);

        return ResponseEntity.ok(
                TrajetoMapper.toResponseDto(
                        trajetoService.atualizar(
                                trajetoAtualizado
                        )
                )
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        trajetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
