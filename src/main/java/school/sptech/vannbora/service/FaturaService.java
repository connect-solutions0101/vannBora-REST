package school.sptech.vannbora.service;

import java.util.List;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.FaturaRepository;

@Service
@RequiredArgsConstructor
public class FaturaService {
    
    private final FaturaRepository faturaRepository;

    private final ResponsavelDependenteService responsavelDependenteService;

    public Fatura salvar(Fatura fatura, int responsavelDependenteId, int dependenteId) {
        fatura.setResponsavelDependente(responsavelDependenteService.buscarPorId(responsavelDependenteId, dependenteId));
        fatura.setDataPagamento(LocalDate.now());
        return faturaRepository.save(fatura);
    }

    public List<Fatura> listar() {
        return faturaRepository.findAll();
    }

    public Fatura buscarPorId(int id) {
        return faturaRepository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Fatura não encontrada")
        );
    }

    public List<Fatura> listarPorIdDependente(int dependenteId) {
        return faturaRepository.findAllByResponsavelDependenteDependenteId(dependenteId);
    }

    public List<Fatura> listarPorIdResponsavel(int responsavelId) {
        return faturaRepository.findAllByResponsavelDependenteResponsavelId(responsavelId);
    }

    public Fatura atualizar(Fatura fatura, int responsavelDependenteId, int dependenteId) {
        Fatura faturaAtual = faturaRepository.findById(fatura.getId()).orElseThrow(
            () -> new RegistroNaoEncontradoException("Fatura não encontrada")
        );

        faturaAtual.setResponsavelDependente(responsavelDependenteService.buscarPorId(responsavelDependenteId, dependenteId));
        faturaAtual.setValor(fatura.getValor());
        faturaAtual.setPago(fatura.getPago());
        faturaAtual.setDataVencimento(fatura.getDataVencimento());
        faturaAtual.setDataPagamento(fatura.getDataPagamento());

        return faturaRepository.save(faturaAtual);
    }

    public void deletar(int id) {
        faturaRepository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Fatura não encontrada")
        );
        faturaRepository.deleteById(id);
    }
}