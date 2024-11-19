package school.sptech.vannbora.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.entidade.RegistroFatura;
import school.sptech.vannbora.repository.RegistroFaturaRepository;

@Service
@RequiredArgsConstructor
public class RegistroFaturaService {

    private final RegistroFaturaRepository registroFaturaRepository;

    public RegistroFatura salvar(RegistroFatura registroFatura) {
        return registroFaturaRepository.save(registroFatura);
    }

    public List<RegistroFatura> listarPorFaturaId(int faturaId) {
        return registroFaturaRepository.findAllByFaturaId(faturaId);
    }
}
