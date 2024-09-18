package school.sptech.vannbora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.repository.EnderecoRepository;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> listar() {
        return repository.findAll();
    }

    public Endereco buscarPorId(int id){
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Endereco buscarPorCep(String cep){
        return repository.findByCep(cep).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Endereco cadastrar(Endereco endereco) {
        return repository.save(endereco);
    }

    public Endereco atualizar(int id, Endereco endereco) {
        repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        endereco.setId(id);
        return repository.save(endereco);
    }

    public void deletar(int id) {
        repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        repository.deleteById(id);
    }
}
