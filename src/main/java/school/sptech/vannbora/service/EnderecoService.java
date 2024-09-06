package school.sptech.vannbora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.repository.EnderecoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public ResponseEntity<List<Endereco>> listar() {
        List<Endereco> enderecos = repository.findAll();
        return ResponseEntity.status(200).body(enderecos);
    }

    public ResponseEntity<Endereco> buscarPorId(int id){
        Optional<Endereco> possivelEndereco = repository.findById(id);
        if(possivelEndereco.isPresent()){
            Endereco endereco = possivelEndereco.get();
            return ResponseEntity.status(200).body(endereco);
        }
        return ResponseEntity.status(404).build();
    }

    public  ResponseEntity<Endereco> buscarPorCep(String cep){
        Endereco endereco = repository.findByCep(cep);
        if(endereco != null){
            return ResponseEntity.status(200).body(endereco);
        }
        return ResponseEntity.status(404).build();
    }

    public ResponseEntity<Endereco> cadastrar(Endereco endereco) {
        Endereco novoEndereco = repository.save(endereco);
        return ResponseEntity.status(201).body(novoEndereco);
    }

    public ResponseEntity<Endereco> atualizar(int id, Endereco endereco) {
        endereco.setId(id);
        Endereco enderecoAtualizado = repository.save(endereco);
        return ResponseEntity.status(200).body(enderecoAtualizado);
    }

    public ResponseEntity<Void> deletar(int id) {
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
