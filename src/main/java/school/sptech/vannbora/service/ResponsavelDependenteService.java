package school.sptech.vannbora.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.Responsavel;
import school.sptech.vannbora.entidade.ResponsavelDependente;
import school.sptech.vannbora.entidade.ResponsavelDependente.ResponsavelDependenteId;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.ResponsavelDependenteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponsavelDependenteService {
    
    private final ResponsavelDependenteRepository responsavelDependenteRepository;

    private final ResponsavelService responsavelService;

    private final DependenteService dependenteService;

    public List<ResponsavelDependente> listar(){
        return responsavelDependenteRepository.findAll();
    }

    public ResponsavelDependente buscarPorId(int responsavelId, int dependenteId){
        return responsavelDependenteRepository.findById(
            new ResponsavelDependente.ResponsavelDependenteId(
                responsavelId,
                dependenteId
            )
        ).orElseThrow(
            () -> new RegistroNaoEncontradoException("Responsável dependente não encontrado")
        );
    }

    public ResponsavelDependente cadastrar(ResponsavelDependente responsavelDependente ,int responsavelId, int dependenteId) {
        Responsavel responsavel = responsavelService.buscarPorId(responsavelId);
        responsavelDependente.setResponsavel(responsavel);

        Dependente dependente = dependenteService.buscarPorId(dependenteId);
        responsavelDependente.setDependente(dependente);
        
        responsavelDependente.setId(
            new ResponsavelDependente.ResponsavelDependenteId(
                responsavelId,
                dependenteId
            )
        );

        return responsavelDependenteRepository.save(responsavelDependente);
    }

    public ResponsavelDependente atualizar(ResponsavelDependente responsavelDependente, int responsavelId, int dependenteId){
        Responsavel responsavel = responsavelService.buscarPorId(responsavelId);
        responsavelDependente.setResponsavel(responsavel);

        Dependente dependente = dependenteService.buscarPorId(dependenteId);
        responsavelDependente.setDependente(dependente);
       
        ResponsavelDependente responsavelDependenteAtual = responsavelDependenteRepository.findById(
            new ResponsavelDependente.ResponsavelDependenteId(
                responsavelId,
                dependenteId
            )
        ).orElseThrow(
            () -> new RegistroNaoEncontradoException("Responsável dependente não encontrado")
        );

        responsavelDependenteAtual.setTipoResponsavel(responsavelDependente.getTipoResponsavel());

        return responsavelDependenteRepository.save(responsavelDependenteAtual);
    }

    public void deletar(int responsavelId, int dependenteId){
        ResponsavelDependenteId responsavelDependente = new ResponsavelDependente.ResponsavelDependenteId(
                responsavelId,
                dependenteId
            );
            
        responsavelDependenteRepository.findById(
            responsavelDependente
        ).orElseThrow(
            () -> new RegistroNaoEncontradoException("Responsável dependente não encontrado")
        );

        responsavelDependenteRepository.deleteById(
            responsavelDependente
        );
    }
}
