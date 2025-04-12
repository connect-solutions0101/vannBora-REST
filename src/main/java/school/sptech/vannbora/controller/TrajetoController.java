package school.sptech.vannbora.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

//    @PutMapping("/{id}")
//    public ResponseEntity<Trajeto> atualizar(@PathVariable int id, @Valid @RequestBody TrajetoRequestDto trajeto){
//        Trajeto trajetoAtualizado = TrajetoMapper.toTrajeto(trajeto);
//
//        List<ResponsavelDependenteIdRequestDto> dependentes = trajeto.trajetoDependentes().stream()
//                .map( ids -> new ResponsavelDependenteIdRequestDto(
//                                ids.idResponsavel(),
//                                ids.idDependente()
//                        )
//                )
//                .toList();
//
//        return ResponseEntity.ok(
////                TrajetoMapper.toResponseDto(
//                        trajetoService.atualizar(
//                                id,
//                                trajetoAtualizado,
//                                dependentes
//                        )
////                )
//        );
//    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        trajetoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
