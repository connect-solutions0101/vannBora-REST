package school.sptech.vannbora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.repository.ProprietarioServicoRepository;

import java.util.List;

@Service
public class ProprietarioServicoService {
    
    @Autowired
    private ProprietarioServicoRepository repository;

    public List<ProprietarioServico> listar() {
        return repository.findAll();
    }

    public ProprietarioServico buscarPorId(int id){
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public ProprietarioServico cadastrar(ProprietarioServico proprietarioServico) {
        return repository.save(proprietarioServico);
    }

    public ProprietarioServico atualizar(int id, ProprietarioServico proprietarioServico) {
        repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        proprietarioServico.setId(id);
        return repository.save(proprietarioServico);
    }

    public void deletar(int id) {
        repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        repository.deleteById(id);
    }
}
