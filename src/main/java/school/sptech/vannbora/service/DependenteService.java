package school.sptech.vannbora.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import school.sptech.vannbora.repository.DependenteRepository;
import school.sptech.vannbora.dto.dependente.DependenteResponsavelEnderecoFaturaRequestDto.Fatura;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.entidade.Responsavel;
import school.sptech.vannbora.entidade.ResponsavelDependente;
import school.sptech.vannbora.enums.Pago;
import school.sptech.vannbora.enums.TipoResponsavel;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;

@Service
public class DependenteService {
    
    private final DependenteRepository repository;

    private final EscolaService escolaService;

    private final ProprietarioServicoService proprietarioServicoService;

    private final FaturaService faturaService;

    private final ResponsavelService responsavelService;

    private final ResponsavelDependenteService responsavelDependenteService;

    private final EnderecoService enderecoService;

    public DependenteService(DependenteRepository repository, @Lazy EscolaService escolaService, @Lazy ProprietarioServicoService proprietarioServicoService, @Lazy FaturaService faturaService, @Lazy ResponsavelService responsavelService, @Lazy ResponsavelDependenteService responsavelDependenteService, @Lazy EnderecoService enderecoService) {
        this.repository = repository;
        this.escolaService = escolaService;
        this.proprietarioServicoService = proprietarioServicoService;
        this.faturaService = faturaService;
        this.responsavelService = responsavelService;
        this.responsavelDependenteService = responsavelDependenteService;
        this.enderecoService = enderecoService;
    }

    public List<Dependente> listar() {
        return repository.findAll();
    }

    public List<Dependente> listarFull(int id) {
        return repository.findAllByProprietarioServicoId(id);
    }

    public Dependente salvar(Dependente dependente, int escolaId, int proprietarioServicoId) {
        Escola escola = escolaService.buscarPorId(escolaId);
        dependente.setEscola(escola);

        ProprietarioServico proprietarioServico = proprietarioServicoService.buscarPorId(proprietarioServicoId);
        dependente.setProprietarioServico(proprietarioServico);

        dependente.setDataCadastro(LocalDate.now());

        return repository.save(dependente);
    }

    public Dependente atualizar(int id, Dependente dependente, int escolaId, int proprietarioServicoId) {
        Dependente dependenteAtual = repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Dependente não encontrado")
        );

        Escola escola = escolaService.buscarPorId(escolaId);
        dependenteAtual.setEscola(escola);

        ProprietarioServico proprietarioServico = proprietarioServicoService.buscarPorId(proprietarioServicoId);
        dependenteAtual.setProprietarioServico(proprietarioServico);

        dependenteAtual.setNome(dependente.getNome());
        dependenteAtual.setDataNascimento(dependente.getDataNascimento());
        dependenteAtual.setTurma(dependente.getTurma());
        dependenteAtual.setTurno(dependente.getTurno());
        dependenteAtual.setCondicao(dependente.getCondicao());
        
        return repository.save(dependenteAtual);
    }

    public Dependente atualizarFull(int id, Dependente dependenteEntity, Integer escolaId, Fatura fatura) {
        Dependente dependenteAtual = repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Dependente não encontrado")
        );

        Escola escola = escolaService.buscarPorId(escolaId);
        dependenteAtual.setEscola(escola);

        ResponsavelDependente responsavelFinanceiro = dependenteEntity.getResponsaveis().stream()
            .filter(responsavel -> responsavel.getTipoResponsavel().equals(TipoResponsavel.FINANCEIRO))
            .findFirst().orElseThrow(
                () -> new RegistroNaoEncontradoException("Responsável financeiro não encontrado")
            );

        faturaService.atualizar(
            school.sptech.vannbora.entidade.Fatura.builder()
                .id(fatura.id())
                .valor(fatura.valor())
                .diaPagamento(fatura.diaPagamento())
                .quantidadeParcelas(fatura.quantidadeParcelas())
                .build()
        , responsavelFinanceiro.getResponsavel().getId(), dependenteAtual.getId());

        dependenteEntity.getResponsaveis().forEach(
            responsavel -> {
                if (responsavel != null && responsavel.getResponsavel() != null) {
                    responsavelService.atualizar(
                        responsavel.getResponsavel().getId(),
                        responsavel.getResponsavel(),
                        responsavel.getResponsavel().getEndereco().getId()
                    );
                }
            }
        );

        enderecoService.atualizar(
            responsavelFinanceiro.getResponsavel().getEndereco().getId(), responsavelFinanceiro.getResponsavel().getEndereco()
        );

        dependenteAtual.setNome(dependenteEntity.getNome());
        dependenteAtual.setDataNascimento(dependenteEntity.getDataNascimento());
        dependenteAtual.setTurma(dependenteEntity.getTurma());
        dependenteAtual.setTurno(dependenteEntity.getTurno());
        dependenteAtual.setCondicao(dependenteEntity.getCondicao());
        
        return repository.save(dependenteAtual);
    }

    public void deletar(int id) {
        repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Responsável não encontrado")
        );

        repository.deleteById(id);
    }

    public Dependente buscarPorId(int id) {
        return repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Dependente não encontrado")
        );
    }

    public Dependente salvarFull(int idProprietario, Dependente dependenteEntity, Integer escolaId, Responsavel responsavelFinanceiro,
            Responsavel responsavelSecundario, Endereco endereco, school.sptech.vannbora.entidade.Fatura fatura) {
        dependenteEntity.setDataCadastro(LocalDate.now());

        ProprietarioServico proprietarioServico = proprietarioServicoService.buscarPorId(idProprietario);
        
        Endereco enderecoSalvo = enderecoService.cadastrar(endereco);
        

        Escola escola = escolaService.buscarPorId(escolaId);
        dependenteEntity.setEscola(escola);
        dependenteEntity.setProprietarioServico(proprietarioServico);
        Dependente dependenteSalvo = repository.save(dependenteEntity);
        
        responsavelFinanceiro = responsavelService.cadastrar(responsavelFinanceiro, enderecoSalvo.getId(), idProprietario);

        ResponsavelDependente responsavelDependenteFinanceiro = responsavelDependenteService.cadastrar(
            ResponsavelDependente.builder()
            .dependente(dependenteSalvo)
            .responsavel(responsavelFinanceiro)
            .tipoResponsavel(TipoResponsavel.FINANCEIRO)
            .build(), 
            responsavelFinanceiro.getId(), 
            dependenteSalvo.getId()
        );

        if (responsavelSecundario != null) {
            responsavelSecundario = responsavelService.cadastrar(responsavelSecundario, null, idProprietario);

            responsavelDependenteService.cadastrar(
                ResponsavelDependente.builder()
                .dependente(dependenteSalvo)
                .responsavel(responsavelSecundario)
                .tipoResponsavel(TipoResponsavel.PADRAO)
                .build(), 
                responsavelSecundario.getId(), 
                dependenteSalvo.getId()
            );
        }
    
        faturaService.salvar(fatura, responsavelDependenteFinanceiro.getResponsavel().getId(), responsavelDependenteFinanceiro.getDependente().getId());

        return dependenteSalvo;        
    }

    public Integer contarPorProprietarioServicoId(int id) {
        return repository.countByProprietarioServicoId(id);
    }

    public Integer contarPorProprietarioServicoIdEDataPagamento(int id, Pago pago, LocalDate dataInicio, LocalDate dataFim) {
        return repository.countByProprietarioServicoIdAndResponsaveisFaturaRegistroFaturaPagoAndResponsaveisFaturaRegistroFaturaDataPagamentoBetween(id, pago, dataInicio, dataFim);
    }
}
