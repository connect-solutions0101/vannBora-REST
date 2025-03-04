package school.sptech.vannbora.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.entidade.Trajeto;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.TrajetoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrajetoService {

    private final TrajetoRepository trajetoRepository;

    public List<Trajeto> listar() {return trajetoRepository.findAll();}

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

    public Trajeto atualizar(Trajeto trajeto){
        Trajeto trajetoAtual = trajetoRepository.findById(trajeto.getId()).orElseThrow(
                () -> new RegistroNaoEncontradoException("Trajeto não encontrado")
        );

        trajetoAtual.setNome(trajeto.getNome());
        trajetoAtual.setPeriodo(trajeto.getPeriodo());
        trajetoAtual.setTrajetoDependentes(trajeto.getTrajetoDependentes());
        trajetoAtual.setProprietarioServico(trajeto.getProprietarioServico());

        return trajetoRepository.save(trajetoAtual);
    }
}
