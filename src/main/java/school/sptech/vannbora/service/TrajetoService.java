package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.entidade.Trajeto;
import school.sptech.vannbora.repository.TrajetoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrajetoService {

    private final TrajetoRepository trajetoRepository;

    public Trajeto salvar(Trajeto trajeto) {
        return trajetoRepository.save(trajeto);
    }

    public void deletar(Trajeto trajeto) {
        trajetoRepository.delete(trajeto);
    }

    public List<Trajeto> buscarPorProprietarioServico(Integer id) {
        return trajetoRepository.findByProprietarioServicoId(id);
    }
}
