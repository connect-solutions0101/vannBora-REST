package school.sptech.vannbora.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.dependente.DependenteRequestDto;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.PilhaObj;
import school.sptech.vannbora.mapper.DependenteMapper;

@RestController
@RequestMapping("/trajeto")
@RequiredArgsConstructor
public class TrajetoController {

    PilhaObj<Dependente> pilha = new PilhaObj<>(30);

    @GetMapping("/")
    public ResponseEntity<Dependente[]> trajeto(){
        if(pilha.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(pilha.getPilha());
    }

    @PostMapping("/embarque")
    public ResponseEntity embarque(@RequestBody DependenteRequestDto dto){
        if(pilha.isFull()) return ResponseEntity.status(429).body("A pilha está. Desembarque um dependente para embarcar outro.");

        Dependente dependente = DependenteMapper.toDependente(dto);
        pilha.push(dependente);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/desembarque")
    public ResponseEntity desembarque(){
        if (pilha.isEmpty()) {
            return ResponseEntity.status(404).body("A pilha está vazia.");
        }

        pilha.pop();
        return ResponseEntity.ok().build();
    }
}
