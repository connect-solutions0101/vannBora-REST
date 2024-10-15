package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.EscolaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EscolaService {

    private final EscolaRepository repository;

    private final EnderecoService enderecoService;

    public List<Escola> listar(){
        return repository.findAll();
    }

    public Escola buscarPorId(int id){
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Escola não encontrada")
        );
    }

    public Escola cadastrar(Escola escola, int enderecoId){
        Endereco endereco = enderecoService.buscarPorId(enderecoId);
        escola.setEndereco(endereco);

        return repository.save(escola);
    }

    public Escola atualizar(int id, Escola escola, int enderecoId){
        Escola escolaAtual = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Escola não encontrada")
        );

        Endereco endereco = enderecoService.buscarPorId(id);
        escolaAtual.setEndereco(endereco);
        escolaAtual.setNome(escola.getNome());
        escolaAtual.setNomeResponsavel(escola.getNomeResponsavel());
        escolaAtual.setTelefone(escola.getTelefone());
        escolaAtual.setTelefoneResponsavel(escola.getTelefoneResponsavel());
        return repository.save(escolaAtual);
    }

    public void deletar(int id){
        repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Escola não encontrada")
        );

        repository.deleteById(id);
    }
}
