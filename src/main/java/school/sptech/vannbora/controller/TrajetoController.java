package school.sptech.vannbora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.dependente.DependenteResponseResumoDto;
import school.sptech.vannbora.dto.trajeto.TrajetoResponseDto;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.FilaObj;
import school.sptech.vannbora.entidade.PilhaObj;
import school.sptech.vannbora.mapper.TrajetoMapper;
import school.sptech.vannbora.service.DependenteService;
import school.sptech.vannbora.service.TrajetoService;

import java.util.List;

@RestController
@RequestMapping("/trajeto")
@RequiredArgsConstructor
public class TrajetoController {

    private final TrajetoService trajetoService;

    @GetMapping("/{id}")
    public ResponseEntity<List<TrajetoResponseDto>> trajeto(
            @PathVariable Integer id
    ){
        List<TrajetoResponseDto> listaTrajetos = trajetoService.buscarPorProprietarioServico(id).stream().map(TrajetoMapper::toResponseDto).toList();
        return ResponseEntity.ok(listaTrajetos);
    }

//    private final DependenteService dependenteService;
//
//    PilhaObj<Dependente> pilha = new PilhaObj<>(30);
//    private final FilaObj<Dependente> fila = new FilaObj<>(new Dependente[30], 0, -1, 0);
//
//
//    @GetMapping("/")
//    public ResponseEntity<DependenteResponseResumoDto[]> trajeto(){
//        if(fila.isEmpty()) return ResponseEntity.noContent().build();
//
//        DependenteResponseResumoDto[] dependentes = new DependenteResponseResumoDto[fila.getTamanho()];
//        for (int i = 0; i < fila.getTamanho(); i++) {
//            Dependente dependente = fila.poll();
//            dependentes[i] = new DependenteResponseResumoDto(dependente.getId(), dependente.getNome());
//            fila.insert(dependente);
//        }
//
//        return ResponseEntity.ok(dependentes);
//    }
//
//    @PostMapping("/embarque/{id}")
//    public ResponseEntity embarque(@PathVariable int id){
//        if(pilha.isFull()) return ResponseEntity.status(429).body("A pilha está cheia. Desembarque um dependente para embarcar outro.");
//
//        Dependente dependente = dependenteService.buscarPorId(id);
//        pilha.push(dependente);
//        fila.insert(dependente);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("/desembarque")
//    public ResponseEntity desembarque(){
//        if (pilha.isEmpty()) {
//            return ResponseEntity.status(404).body("A pilha está vazia.");
//        }
//
//        Dependente dependenteDesembarcado = pilha.pop();
//
//        return ResponseEntity.ok().build();
//    }
}
