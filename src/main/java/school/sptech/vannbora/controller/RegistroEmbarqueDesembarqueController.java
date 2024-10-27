package school.sptech.vannbora.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.registroembarquedesembarque.RegistroEmbarqueDesembarqueRequestDto;
import school.sptech.vannbora.dto.registroembarquedesembarque.RegistroEmbarqueDesembarqueResponseDto;
import school.sptech.vannbora.entidade.RegistroEmbarqueDesembarque;
import school.sptech.vannbora.mapper.RegistroEmbarqueDesembarqueMapper;
import school.sptech.vannbora.service.RegistroEmbarqueDesembarqueService;

import java.util.List;

@RestController
@RequestMapping("/registros-embarques-desembarques")
@RequiredArgsConstructor
public class RegistroEmbarqueDesembarqueController {

    private final RegistroEmbarqueDesembarqueService service;

    @Operation(summary = "Listar registros de embarques e desembarques", description = "Método lista os registros de embarques e desembarques", tags = "Registro embarque desembarque Controller")
    @GetMapping
    public ResponseEntity<List<RegistroEmbarqueDesembarqueResponseDto>> listar(){
        List<RegistroEmbarqueDesembarque> registros = service.listar();

        if(registros.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<RegistroEmbarqueDesembarqueResponseDto> dtoLista = registros.stream()
                .map(RegistroEmbarqueDesembarqueMapper::toResponseDto)
                .toList();

        return ResponseEntity.ok(dtoLista);
    }

    @Operation(summary = "Busca registro de embarque e desembarque por id", description = "Método busca o registros de embarque e desembarque por id", tags = "Registro embarque desembarque Controller")
    @GetMapping("/{id}")
    public ResponseEntity<RegistroEmbarqueDesembarqueResponseDto> buscarPorId(@PathVariable int id){
        RegistroEmbarqueDesembarque registro = service.buscarPorId(id);
        return ResponseEntity.ok(RegistroEmbarqueDesembarqueMapper.toResponseDto(registro));
    }

    @Operation(summary = "Cadastrar registro de embarque e desembarque", description = "Método cadastra o registro de embarque e desembarque", tags = "Registro embarque desembarque Controller")
    @PostMapping
    public ResponseEntity<RegistroEmbarqueDesembarqueResponseDto> cadastrar(@Valid @RequestBody RegistroEmbarqueDesembarqueRequestDto dto){
        RegistroEmbarqueDesembarque registro = RegistroEmbarqueDesembarqueMapper.toEntity(dto);

        return ResponseEntity.created(null).body(RegistroEmbarqueDesembarqueMapper.toResponseDto(service.salvar(registro, dto.responsavelId(), dto.dependenteId())));
    }

    @Operation(summary = "Atualizar registro de embarque e desembarque", description = "Método atualiza o registro de embarque e desembarque", tags = "Registro embarque desembarque Controller")
    @PutMapping
    public ResponseEntity<RegistroEmbarqueDesembarqueResponseDto> atualizar(@PathVariable int id, @Valid @RequestBody RegistroEmbarqueDesembarqueRequestDto dto){
        RegistroEmbarqueDesembarque registro = RegistroEmbarqueDesembarqueMapper.toEntity(dto);

        return ResponseEntity.ok(RegistroEmbarqueDesembarqueMapper.toResponseDto(service.atualizar(id, registro, dto.responsavelId(), dto.dependenteId())));
    }

    @Operation(summary = "Deletar registro de embarque e desembarque", description = "Método deleta o registro de embarque e desembarque", tags = "Registro embarque desembarque Controller")
    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
