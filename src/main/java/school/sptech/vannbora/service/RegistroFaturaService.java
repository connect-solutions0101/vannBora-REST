package school.sptech.vannbora.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.entidade.RegistroFatura;
import school.sptech.vannbora.enums.Pago;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.RegistroFaturaRepository;

@Service
@RequiredArgsConstructor
public class RegistroFaturaService {

    private final RegistroFaturaRepository registroFaturaRepository;

    private final FaturaService faturaService;

    public RegistroFatura salvar(RegistroFatura registroFatura, int faturaId) {
        registroFatura.setFatura(faturaService.buscarPorId(faturaId));
        return registroFaturaRepository.save(registroFatura);
    }

    public RegistroFatura atualizar(int id, RegistroFatura registroFatura) {
        RegistroFatura registroFaturaAtual = registroFaturaRepository.findById(id).orElseThrow(
            () -> new RegistroNaoEncontradoException("Registro da fatura não encontrado")
        );

        registroFaturaAtual.setPago(registroFatura.getPago());
        return registroFaturaRepository.save(registroFaturaAtual);
    }

    public List<RegistroFatura> listarPorFaturaId(int faturaId) {
        return registroFaturaRepository.findAllByFaturaId(faturaId);
    }

    public BigDecimal somarValorPorPeriodo(int proprietarioServicoId, LocalDate dataInicio, LocalDate dataFim) {
        return registroFaturaRepository.findSumFaturaValorByFaturaResponsavelDependenteDependenteProprietarioServicoIdAndDataPagamentoBetween(proprietarioServicoId, dataInicio, dataFim);
    }

    public BigDecimal somarValorPagoPorPeriodo(int proprietarioServicoId, Pago pago, LocalDate dataInicio, LocalDate dataFim) {
        return registroFaturaRepository.findSumFaturaValorByPagoAndFaturaResponsavelDependenteDependenteProprietarioServicoIdAndDataPagamentoBetween(pago, proprietarioServicoId, dataInicio, dataFim);
    }

    public Map<Integer, BigDecimal> calcularRecebimentoRealDoAnoAtualPorMes(int id, Pago pago) {
        List<Object[]> results = registroFaturaRepository.findRecebimentoRealDoAnoAtualPorMes(id, pago);
        return results.stream().collect(Collectors.toMap(
            result -> (Integer) result[0],
            result -> BigDecimal.valueOf((Double) result[1])
        ));
    }

    public Map<Integer, BigDecimal> calcularRecebimentoEsperadoDoAnoAtualPorMes(int id) {
        List<Object[]> results = registroFaturaRepository.findRecebimentoEsperadoDoAnoAtualPorMes(id);
        return results.stream().collect(Collectors.toMap(
            result -> (Integer) result[0],
            result -> BigDecimal.valueOf((Double) result[1])
        ));
    }   

    public Map<Integer, BigDecimal> calcularRecebimentoRealDoMesAtualPorDia(int id, Pago pago) {
        List<Object[]> results = registroFaturaRepository.findRecebimentoRealDoMesAtualPorDia(id, pago);
        return results.stream().collect(Collectors.toMap(
            result -> (Integer) result[0],
            result -> BigDecimal.valueOf((Double) result[1])
        ));
    }
     
    public Map<Integer, BigDecimal> calcularRecebimentoEsperadoDoMesAtualPorDia(int id) {
        List<Object[]> results = registroFaturaRepository.findRecebimentoEsperadoDoMesAtualPorDia(id);
        return results.stream().collect(Collectors.toMap(
            result -> (Integer) result[0],
            result -> BigDecimal.valueOf((Double) result[1])
        ));
    }
}
