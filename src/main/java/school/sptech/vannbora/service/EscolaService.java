package school.sptech.vannbora.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.EscolaRepository;

import java.util.List;

@Service
public class EscolaService {

    private final EscolaRepository repository;

    private final EnderecoService enderecoService;

    private final ProprietarioServicoService proprietarioServicoService;

    private final RegistroFaturaService registroFaturaService;

    public EscolaService(EscolaRepository repository, 
                         @Lazy EnderecoService enderecoService, 
                         @Lazy ProprietarioServicoService proprietarioServicoService,
                        @Lazy RegistroFaturaService registroFaturaService
                         ) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.proprietarioServicoService = proprietarioServicoService;
        this.registroFaturaService = registroFaturaService;
    }

    public List<Escola> listar(){
        return repository.findAll();
    }

    public List<Escola> listarPorProprietario(int id){
        return repository.findAllByProprietarioServicoId(id);
    }

    public List<Escola> listarPorProprietarioAndFiltrarNome(int id, String nome) {
        return repository.findAllByProprietarioServicoIdAndNomeContaining(id, nome);
    }

    public int contarPagamentosPendentesPorId(int id){
        return registroFaturaService.contarPagamentosPendentesPorEscolaId(id);
    }

    public Escola buscarPorId(int id){
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Escola não encontrada")
        );
    }

    public Escola cadastrar(Escola escola, int enderecoId, int proprietarioServicoId){
        Endereco endereco = enderecoService.buscarPorId(enderecoId);
        escola.setEndereco(endereco);

        ProprietarioServico proprietarioServico = proprietarioServicoService.buscarPorId(proprietarioServicoId);
        escola.setProprietarioServico(proprietarioServico);

        return repository.save(escola);
    }

    
    public Escola cadastrar(Escola escola, int proprietarioServicoId) {
        Endereco endereco = 
            Endereco.builder()
            .cep(escola.getEndereco().getCep())
            .bairro(escola.getEndereco().getBairro())
            .logradouro(escola.getEndereco().getLogradouro())
            .cidade(escola.getEndereco().getCidade())
            .numero(escola.getEndereco().getNumero())
            .build();
        endereco = enderecoService.cadastrar(endereco);
        
        escola.setEndereco(endereco);

        ProprietarioServico proprietarioServico = proprietarioServicoService.buscarPorId(proprietarioServicoId);
        escola.setProprietarioServico(proprietarioServico);

        return repository.save(escola);
    }

    public Escola atualizar(int id, Escola escola){
        Escola escolaAtual = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Escola não encontrada")
        );

        escolaAtual.setEndereco(enderecoService.atualizar(escola.getEndereco().getId(), escola.getEndereco()));
        
        escolaAtual.setNome(escola.getNome());
        escolaAtual.setNomeResponsavel(escola.getNomeResponsavel());
        escolaAtual.setTelefone(escola.getTelefone());
        escolaAtual.setTelefoneResponsavel(escola.getTelefoneResponsavel());
        return repository.save(escolaAtual);
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

    public Integer contarEscolasPorProprietario(int id){
        return repository.countByProprietarioServicoId(id);
    }
}
