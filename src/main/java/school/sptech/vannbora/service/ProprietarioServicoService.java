package school.sptech.vannbora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.ProprietarioServicoRepository;

import java.util.List;

@Service
public class ProprietarioServicoService {
    
    @Autowired
    private ProprietarioServicoRepository repository;

    public List<ProprietarioServico> listar() {
        return repository.findAll();
    }

    public ProprietarioServico buscarPorId(int id){
        return repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Proprietário de serviço não encontrado")
        );
    }

    public ProprietarioServico cadastrar(ProprietarioServico proprietarioServico) {
        return repository.save(proprietarioServico);
    }

    public ProprietarioServico atualizar(int id, ProprietarioServico proprietarioServico) {
        repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Proprietário de serviço não encontrado")
        );

        proprietarioServico.setId(id);
        return repository.save(proprietarioServico);
    }

    public void deletar(int id) {
        repository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Proprietário de serviço não encontrado")
        );

        repository.deleteById(id);
    }
}
