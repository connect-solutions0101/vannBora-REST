package school.sptech.vannbora.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.repository.DependenteRepository;

import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;

@Service
@RequiredArgsConstructor
public class DependenteService {
    
    private final DependenteRepository repository;

    private final EscolaService escolaService;

    private final ProprietarioServicoService proprietarioServicoService;

    public List<Dependente> listar() {
        return repository.findAll();
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
}
