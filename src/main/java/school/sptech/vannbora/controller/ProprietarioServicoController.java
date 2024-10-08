package school.sptech.vannbora.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import school.sptech.vannbora.dto.proprietario.ProprietarioServicoRequestDto;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoResponseDto;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.mapper.ProprietarioServicoMapper;
import school.sptech.vannbora.service.ProprietarioServicoService;

@RestController
@RequestMapping("/proprietarios-servicos")
public class ProprietarioServicoController {
    
    @Autowired
    private ProprietarioServicoService service;

    @GetMapping
    public ResponseEntity<List<ProprietarioServicoResponseDto>> listar(){
        List<ProprietarioServico> lista = service.listar();

        if(lista.isEmpty()) return ResponseEntity.status(204).build();

        List<ProprietarioServicoResponseDto> dtoLista = new ArrayList<>();
        for(ProprietarioServico proprietario : lista){
            dtoLista.add(ProprietarioServicoMapper.toProprietarioServicoResponseDto(proprietario));
        }

        return ResponseEntity.status(200).body(dtoLista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProprietarioServicoResponseDto> buscarPorId(@PathVariable int id){
        return ResponseEntity.status(200).body(ProprietarioServicoMapper.toProprietarioServicoResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProprietarioServicoResponseDto> atualizar(@PathVariable int id, @RequestBody @Valid ProprietarioServicoRequestDto dto){
        ProprietarioServico proprietarioEditado = ProprietarioServicoMapper.toProprietarioServicoAtualizar(id, dto);
        return ResponseEntity.status(200).body(ProprietarioServicoMapper.toProprietarioServicoResponseDto(service.atualizar(id, proprietarioEditado)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }
}

