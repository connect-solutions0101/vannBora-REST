package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.entidade.TrajetoDependente;
import school.sptech.vannbora.entidade.TrajetoDependente.TrajetoDependenteId;
import school.sptech.vannbora.repository.TrajetoDepenteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrajetoDependenteService {

    private final TrajetoDepenteRepository trajetoDepenteRepository;

    public TrajetoDependente salvar(TrajetoDependente trajetoDependente) {
        return trajetoDepenteRepository.save(trajetoDependente);
    }

    public void deletar(TrajetoDependente trajetoDependente) {
        trajetoDepenteRepository.delete(trajetoDependente);
    }
}
