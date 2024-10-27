package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.entidade.RegistroEmbarqueDesembarque;
import school.sptech.vannbora.entidade.ResponsavelDependente;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.RegistroEmbarqueDesembarqueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistroEmbarqueDesembarqueService {

    private final RegistroEmbarqueDesembarqueRepository repository;
    private final ResponsavelDependenteService responsavelDependenteService;

    public List<RegistroEmbarqueDesembarque> listar(){
        return repository.findAll();
    }

    public RegistroEmbarqueDesembarque buscarPorId(int id){
        return repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Registro embarque desembarque não encontrado")
        );
    }

    public RegistroEmbarqueDesembarque salvar(RegistroEmbarqueDesembarque registroEmbarqueDesembarque, int responsavelId, int dependenteId){
        ResponsavelDependente responsavelDependente = responsavelDependenteService.buscarPorId(responsavelId, dependenteId);
        registroEmbarqueDesembarque.setResponsavelDependente(responsavelDependente);

        return repository.save(registroEmbarqueDesembarque);
    }

    public RegistroEmbarqueDesembarque atualizar(int id, RegistroEmbarqueDesembarque registroEmbarqueDesembarque, int responsavelId, int dependenteId){
        RegistroEmbarqueDesembarque registroEmbarqueDesemarqueAtual = repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Registro embarque desembarque não encontrado")
        );

        ResponsavelDependente responsavelDependente = responsavelDependenteService.buscarPorId(responsavelId, dependenteId);
        registroEmbarqueDesemarqueAtual.setResponsavelDependente(responsavelDependente);

        registroEmbarqueDesemarqueAtual.setDataHora(registroEmbarqueDesembarque.getDataHora());
        registroEmbarqueDesemarqueAtual.setTipo(registroEmbarqueDesembarque.getTipo());

        return repository.save(registroEmbarqueDesemarqueAtual);
    }

    public void deletar(int id){
        repository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Registro embarque desembarque não encontrado")
        );

        repository.deleteById(id);
    }

}
