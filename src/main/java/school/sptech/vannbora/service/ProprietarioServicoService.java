package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.ProprietarioServicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProprietarioServicoService {

    private final ProprietarioServicoRepository repository;

    public List<ProprietarioServico> listar() {
        return repository.findAll();
    }

    public ProprietarioServico buscarPorId(int id){
        return repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Proprietário de serviço não encontrado")
        );
    }

    public ProprietarioServico atualizar(int id, ProprietarioServico proprietarioServico) {
        ProprietarioServico proprietarioServicoAtual = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Proprietário de serviço não encontrado")
        );

        proprietarioServicoAtual.setNome(proprietarioServico.getNome());
        proprietarioServicoAtual.setEmail(proprietarioServico.getEmail());
        proprietarioServicoAtual.setCpf(proprietarioServico.getCpf());
        proprietarioServicoAtual.setSenha(proprietarioServico.getSenha());
        proprietarioServicoAtual.setRole(proprietarioServico.getRole());

        return repository.save(proprietarioServicoAtual);
    }

    public void deletar(int id) {
        repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Proprietário de serviço não encontrado")
        );

        repository.deleteById(id);
    }
}
