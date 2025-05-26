package school.sptech.vannbora.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteIdRequestDto;
import school.sptech.vannbora.dto.trajeto.TrajetoRequestDto;
import school.sptech.vannbora.dto.trajeto.TrajetoResponseDto;
import school.sptech.vannbora.entidade.Trajeto;
import school.sptech.vannbora.mapper.TrajetoMapper;
import school.sptech.vannbora.service.TrajetoService;

import java.util.List;

@RestController
@RequestMapping("/trajetos")
@RequiredArgsConstructor
public class TrajetoController {
    private final TrajetoService trajetoService;

    @GetMapping("/{id}")
    public ResponseEntity<List<TrajetoResponseDto>> listar(@PathVariable int id){
        List<Trajeto> trajetos = trajetoService.buscarPorProprietarioServico(id);

        if (trajetos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(trajetos.stream().map(
                TrajetoMapper::toResponseDto
        ).toList());
    }

    @GetMapping("/{id}/single")
    public ResponseEntity<TrajetoResponseDto> listarSingle(@PathVariable int id){
        Trajeto trajeto = trajetoService.buscarPorTrajetoId(id);

        if (trajeto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(TrajetoMapper.toResponseDto(trajeto));
    }

    @PostMapping
    public ResponseEntity<TrajetoResponseDto> salvarFull(@Valid @RequestBody TrajetoRequestDto trajeto){
        Trajeto novoTrajeto = TrajetoMapper.toTrajeto(trajeto);
        List<ResponsavelDependenteIdRequestDto> dependentes = trajeto.trajetoDependentes().stream()
                .map( ids -> new ResponsavelDependenteIdRequestDto(
                        ids.idResponsavel(),
                        ids.idDependente()
                    )
                )
                .toList();

        return ResponseEntity.created(null).body(
                TrajetoMapper.toResponseDto(
                        trajetoService.salvarFull(
                                novoTrajeto
                                , dependentes
                                , trajeto.proprietarioServicoId()
                        )
                )
        );
    }

    @PostMapping("/popular/{trajetoId}")
    public ResponseEntity<TrajetoResponseDto> popular(@PathVariable Integer trajetoId, @RequestBody List<ResponsavelDependenteIdRequestDto> dependentes){
        if(dependentes.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        Trajeto trajeto = trajetoService.popular(trajetoId, dependentes);
        return ResponseEntity.ok(TrajetoMapper.toResponseDto(trajeto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        trajetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
