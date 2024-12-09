package school.sptech.vannbora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.dependente.DependenteRequestDto;
import school.sptech.vannbora.dto.dependente.DependenteResponseDto;
import school.sptech.vannbora.dto.dependente.DependenteResponseResumoDto;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.FilaObj;
import school.sptech.vannbora.entidade.PilhaObj;
import school.sptech.vannbora.mapper.DependenteMapper;

@RestController
@RequestMapping("/trajeto")
@RequiredArgsConstructor
public class TrajetoController {

    PilhaObj<Dependente> pilha = new PilhaObj<>(30);
    private final FilaObj<Dependente> fila = new FilaObj<>(new Dependente[30], 0, -1, 0);


    @GetMapping("/")
    public ResponseEntity<DependenteResponseResumoDto[]> trajeto(){
        if(fila.isEmpty()) return ResponseEntity.noContent().build();

        DependenteResponseResumoDto[] dependentes = new DependenteResponseResumoDto[fila.getTamanho()];
        for (int i = 0; i < fila.getTamanho(); i++) {
            Dependente dependente = fila.poll();
            dependentes[i] = new DependenteResponseResumoDto(dependente.getId(), dependente.getNome());
            fila.insert(dependente);
        }

        return ResponseEntity.ok(dependentes);
    }

    @PostMapping("/embarque")
    public ResponseEntity embarque(@RequestBody DependenteRequestDto dto){
        if(pilha.isFull()) return ResponseEntity.status(429).body("A pilha está cheia. Desembarque um dependente para embarcar outro.");

        Dependente dependente = DependenteMapper.toDependente(dto);
        pilha.push(dependente);
        fila.insert(dependente);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/desembarque")
    public ResponseEntity desembarque(){
        if (pilha.isEmpty()) {
            return ResponseEntity.status(404).body("A pilha está vazia.");
        }

        Dependente dependenteDesembarcado = pilha.pop();
        Dependente dependenteRemovidoDaFila = fila.poll();

        if (!dependenteDesembarcado.equals(dependenteRemovidoDaFila)) {
            return ResponseEntity.status(500).body("Erro de sincronização entre pilha e fila.");
        }

        return ResponseEntity.ok().build();
    }
}
