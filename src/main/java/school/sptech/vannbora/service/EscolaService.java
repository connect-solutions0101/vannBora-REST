package school.sptech.vannbora.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import school.sptech.vannbora.dto.escola.EscolaAlunosResponseDto;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.mapper.EscolaMapper;
import school.sptech.vannbora.repository.EscolaRepository;

import java.util.List;

@Service
public class EscolaService {

    private final EscolaRepository repository;

    private final EnderecoService enderecoService;

    private final ProprietarioServicoService proprietarioServicoService;

    private final FaturaService faturaService;

    public EscolaService(EscolaRepository repository, 
                         @Lazy EnderecoService enderecoService, 
                         @Lazy FaturaService faturaService,
                         @Lazy ProprietarioServicoService proprietarioServicoService
                         ) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.faturaService = faturaService;
        this.proprietarioServicoService = proprietarioServicoService;
    }

    public List<Escola> listar(){
        return repository.findAll();
    }

    public List<EscolaAlunosResponseDto> listarFull(int id){
        List<Escola> escolas = repository.findAllByProprietarioServicoId(id);

        List<EscolaAlunosResponseDto> dtoLista = escolas.stream()
            .map(escola -> EscolaMapper.toEscolaResponseDto(escola, escola.getDependentes().size(), this.contarPagamentosPendentesPorId(escola.getId())))
            .toList();
        
        return dtoLista;
    }

    public int contarPagamentosPendentesPorId(int id){
        return faturaService.contarPorIdDependenteFaturasPendentes(id);
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

    public Escola atualizar(int id, Escola escola){
        Escola escolaAtual = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Escola não encontrada")
        );

        enderecoService.atualizar(escola.getEndereco().getId(), escola.getEndereco());
        
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
}
