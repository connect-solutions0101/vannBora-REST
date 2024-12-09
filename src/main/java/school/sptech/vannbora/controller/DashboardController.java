package school.sptech.vannbora.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import school.sptech.vannbora.dto.dashboard.DashboardDadosResponseDto;
import school.sptech.vannbora.dto.dashboard.DashboardListaDadosResponseDto;
import school.sptech.vannbora.enums.Pago;
import school.sptech.vannbora.service.DependenteService;
import school.sptech.vannbora.service.EscolaService;
import school.sptech.vannbora.service.FaturaService;
import school.sptech.vannbora.service.RegistroFaturaService;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    
    private final DependenteService dependenteService;

    private final EscolaService escolaService;

    private final RegistroFaturaService registroFaturaService;

    private final FaturaService faturaService;

    @GetMapping("/dados/{id}")
    public ResponseEntity<DashboardDadosResponseDto> dados(@PathVariable int id, 
        @RequestParam LocalDate dataInicio, 
        @RequestParam LocalDate dataFim) {
        Integer totalDependentes = dependenteService.contarPorProprietarioServicoId(id);
        Integer totalEscolas = escolaService.contarEscolasPorProprietario(id);
        Integer pagantesMes = dependenteService.contarPorProprietarioServicoIdEDataPagamento(id, Pago.PAGO, dataInicio, dataFim);
        Integer devedoresMes = dependenteService.contarPorProprietarioServicoIdEDataPagamento(id, Pago.NAO_PAGO, dataInicio, dataFim);
        BigDecimal recebimentoEsperadoMes = registroFaturaService.somarValorPorPeriodo(id, dataInicio, dataFim);
        BigDecimal recebimentoRealizadoMes = registroFaturaService.somarValorPagoPorPeriodo(id, Pago.PAGO, dataInicio, dataFim);
        BigDecimal rendaMesAnterior = registroFaturaService.somarValorPagoPorPeriodo(id, Pago.PAGO, dataInicio.minusMonths(1), dataFim.minusMonths(1));
        BigDecimal rendaMediaPorDependente = faturaService.valorMedioPorAluno(id);

        return ResponseEntity.ok(new DashboardDadosResponseDto(
            totalDependentes,
            totalEscolas,
            pagantesMes,
            devedoresMes,
            recebimentoEsperadoMes,
            recebimentoRealizadoMes,
            rendaMesAnterior,
            rendaMediaPorDependente
        ));
    }
    
    @GetMapping("/lista-dados-mes/{id}")
    public ResponseEntity<DashboardListaDadosResponseDto> listaDadosMes(@PathVariable int id) {
        Map<Integer, BigDecimal> realReceiptsMap = registroFaturaService.calcularRecebimentoRealDoAnoAtualPorMes(id, Pago.PAGO);
        Map<Integer, BigDecimal> expectedReceiptsMap = registroFaturaService.calcularRecebimentoEsperadoDoAnoAtualPorMes(id);

        List<DashboardListaDadosResponseDto.InnerResponseDto> realReceipts = realReceiptsMap.entrySet().stream()
            .map(entry -> new DashboardListaDadosResponseDto.InnerResponseDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        List<DashboardListaDadosResponseDto.InnerResponseDto> expectedReceipts = expectedReceiptsMap.entrySet().stream()
            .map(entry -> new DashboardListaDadosResponseDto.InnerResponseDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        DashboardListaDadosResponseDto responseDto = new DashboardListaDadosResponseDto(expectedReceipts, realReceipts);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/lista-dados-dia/{id}")
    public ResponseEntity<DashboardListaDadosResponseDto> listaDadosDia(@PathVariable int id){
        Map<Integer, BigDecimal> realReceiptsMap = registroFaturaService.calcularRecebimentoRealDoMesAtualPorDia(id, Pago.PAGO);
        Map<Integer, BigDecimal> expectedReceiptsMap = registroFaturaService.calcularRecebimentoEsperadoDoMesAtualPorDia(id);

        List<DashboardListaDadosResponseDto.InnerResponseDto> realReceipts = realReceiptsMap.entrySet().stream()
            .map(entry -> new DashboardListaDadosResponseDto.InnerResponseDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        List<DashboardListaDadosResponseDto.InnerResponseDto> expectedReceipts = expectedReceiptsMap.entrySet().stream()
            .map(entry -> new DashboardListaDadosResponseDto.InnerResponseDto(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        DashboardListaDadosResponseDto responseDto = new DashboardListaDadosResponseDto(expectedReceipts, realReceipts);
        return ResponseEntity.ok(responseDto);
    }

}
