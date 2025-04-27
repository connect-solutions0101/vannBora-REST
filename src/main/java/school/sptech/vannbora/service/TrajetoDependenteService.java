package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.entidade.TrajetoDependente;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.TrajetoDepenteRepository;

@Service
@RequiredArgsConstructor
public class TrajetoDependenteService {

    private final TrajetoDepenteRepository trajetoDepenteRepository;

    public TrajetoDependente salvar(TrajetoDependente trajetoDependente) {
        return trajetoDepenteRepository.save(trajetoDependente);
    }

    public TrajetoDependente buscarPorId(Integer id) {
        return trajetoDepenteRepository.findById(id).orElseThrow(
                () -> new RegistroNaoEncontradoException("Trajeto dependente não encontrado")
        );
    }

    public void deletar(TrajetoDependente trajetoDependente) {
        trajetoDepenteRepository.delete(trajetoDependente);
    }
}
