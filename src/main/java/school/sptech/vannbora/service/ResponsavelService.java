package school.sptech.vannbora.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.entidade.Responsavel;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.ResponsavelRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsavelService {
    
    private final ResponsavelRepository repository;

    private final EnderecoService enderecoService;

    private final ProprietarioServicoService proprietarioServicoService;

    public List<Responsavel> listar(){
        return repository.findAll();
    }

    public Responsavel buscarPorId(int id){
        return repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Responsável não encontrado")
        );
    }

    public Responsavel cadastrar(Responsavel responsavel, int enderecoId, int proprietarioServicoId){
        Endereco endereco = enderecoService.buscarPorId(enderecoId);
        responsavel.setEndereco(endereco);

        ProprietarioServico proprietarioServico = proprietarioServicoService.buscarPorId(proprietarioServicoId);
        responsavel.setProprietarioServico(proprietarioServico);

        return repository.save(responsavel);
    }

    public Responsavel atualizar(int id, Responsavel responsavel, int enderecoId, int proprietarioServicoId){
        Responsavel responsavelAtual = repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Responsável não encontrado")
        );

        Endereco endereco = enderecoService.buscarPorId(enderecoId);
        responsavelAtual.setEndereco(endereco);

        ProprietarioServico proprietarioServico = proprietarioServicoService.buscarPorId(proprietarioServicoId);
        responsavelAtual.setProprietarioServico(proprietarioServico);

        responsavelAtual.setNome(responsavel.getNome());
        responsavelAtual.setTelefone(responsavel.getTelefone());
        responsavelAtual.setParentesco(responsavel.getParentesco());

        return repository.save(responsavelAtual);
    }

    public void deletar(int id){
        repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Responsável não encontrado")
        );
        repository.deleteById(id);
    }
}
