package school.sptech.vannbora.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.escola.EscolaRequestDto;
import school.sptech.vannbora.dto.escola.EscolaResponseDto;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.mapper.EscolaMapper;
import school.sptech.vannbora.service.EscolaService;

import java.util.List;

@RestController
@RequestMapping("/escolas")
@RequiredArgsConstructor
public class EscolaController {

    private final EscolaService service;

    @GetMapping
    public ResponseEntity<List<EscolaResponseDto>> listar(){
        List<Escola> escolas = service.listar();

        if(escolas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<EscolaResponseDto> dtoLista = escolas.stream()
                .map(EscolaMapper::toEscolaResponseDto)
                .toList();

        return ResponseEntity.ok(dtoLista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscolaResponseDto> buscarPorId(@PathVariable int id){
        Escola escola = service.buscarPorId(id);
        return ResponseEntity.ok(EscolaMapper.toEscolaResponseDto(escola));
    }

    @PostMapping
    public ResponseEntity<EscolaResponseDto> cadastrar(@Valid @RequestBody EscolaRequestDto dto){
        Escola escola = EscolaMapper.toEscola(dto);

        return ResponseEntity.created(null).body(EscolaMapper.toEscolaResponseDto(service.cadastrar(escola, dto.enderecoId())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EscolaResponseDto> atualizar(@PathVariable int id, @Valid @RequestBody EscolaRequestDto dto){
        Escola escola = EscolaMapper.toEscola(dto);

        return ResponseEntity.ok(EscolaMapper.toEscolaResponseDto(service.atualizar(id, escola, dto.enderecoId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
