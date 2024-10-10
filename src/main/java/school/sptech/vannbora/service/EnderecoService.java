package school.sptech.vannbora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
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
            () -> new RegistroNaoEncontradoException("Endereço não encontrado")
        );
    }

    public Endereco buscarPorCep(String cep){
        return repository.findByCep(cep).orElseThrow(
            () -> new RegistroNaoEncontradoException("Endereço não encontrado")
        );
    }

    public Endereco cadastrar(Endereco endereco) {
        return repository.save(endereco);
    }

    public Endereco atualizar(int id, Endereco endereco) {
        Endereco enderecoAtual = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Endereço não encontrado")
        );

        enderecoAtual.setCep(endereco.getCep());
        enderecoAtual.setLogradouro(endereco.getLogradouro());
        enderecoAtual.setBairro(endereco.getBairro());
        enderecoAtual.setCidade(endereco.getCidade());
        enderecoAtual.setPontoReferencia(endereco.getPontoReferencia());
        enderecoAtual.setNumero(endereco.getNumero());

        return repository.save(enderecoAtual);
    }

    public void deletar(int id) {
        repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Endereço não encontrado")
        );

        repository.deleteById(id);
    }
}
