package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteIdRequestDto;
import school.sptech.vannbora.entidade.ResponsavelDependente;
import school.sptech.vannbora.entidade.Trajeto;
import school.sptech.vannbora.entidade.TrajetoDependente;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.TrajetoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrajetoService {
    private final TrajetoRepository trajetoRepository;

    private final ResponsavelDependenteService responsavelDependenteService;

    private final ProprietarioServicoService proprietarioServicoService;

    private final TrajetoDependenteService trajetoDependenteService;

    public List<Trajeto> listar() {
        return trajetoRepository.findAll();
    }

    public Trajeto salvar(Trajeto trajeto) {
        return trajetoRepository.save(trajeto);
    }

    public void deletar(int id) {
        trajetoRepository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Trajeto não encontrado")
        );
        trajetoRepository.deleteById(id);
    }

    public List<Trajeto> buscarPorProprietarioServico(Integer id) {
        return trajetoRepository.findByProprietarioServicoId(id);
    }

    public Trajeto salvarFull(Trajeto novoTrajeto, List<ResponsavelDependenteIdRequestDto> dependentes, Integer proprietarioServicoId) {
        List<ResponsavelDependente> responsavelDependentes = dependentes.stream()
                .map(
                        responsavelDependenteIdRequestDto -> responsavelDependenteService.buscarPorId(
                                responsavelDependenteIdRequestDto.idResponsavel(), responsavelDependenteIdRequestDto.idDependente()
                        )
                )
                .toList();

        List<TrajetoDependente> trajetoDependentes = responsavelDependentes.stream()
                .map(responsavelDependente -> TrajetoDependente.builder()
                        .responsavelDependente(responsavelDependente)
                        .trajeto(novoTrajeto)
                        .build())
                .toList();

        novoTrajeto.setTrajetoDependentes(trajetoDependentes);
        novoTrajeto.setProprietarioServico(
                proprietarioServicoService.buscarPorId(proprietarioServicoId)
        );

        return trajetoRepository.save(novoTrajeto);
    }

    public Trajeto buscarPorTrajetoId(int id) {
        return trajetoRepository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Trajeto não encontrado")
        );
    }

    public Trajeto popular(Integer trajetoId, List<ResponsavelDependenteIdRequestDto> dependentes) {
        Trajeto trajeto = buscarPorTrajetoId(trajetoId);

        List<TrajetoDependente> dependentesTrajeto = trajetoDependenteService.buscarPorTrajetoId(trajetoId);
        dependentesTrajeto.forEach(trajetoDependenteService::deletar);

        List<ResponsavelDependente> responsavelDependentes = dependentes.stream()
                .map(
                        responsavelDependenteIdRequestDto -> responsavelDependenteService.buscarPorId(
                                responsavelDependenteIdRequestDto.idResponsavel(), responsavelDependenteIdRequestDto.idDependente()
                        )
                )
                .toList();

        List<TrajetoDependente> trajetoDependentes = responsavelDependentes.stream()
                .map(responsavelDependente -> TrajetoDependente.builder()
                        .responsavelDependente(responsavelDependente)
                        .trajeto(trajeto)
                        .build())
                .toList();

        trajetoDependentes.forEach(trajetoDependenteService::salvar);

        return buscarPorTrajetoId(trajetoId);
    }
}
