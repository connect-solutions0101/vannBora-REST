package school.sptech.vannbora.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.entidade.RegistroFatura;
import school.sptech.vannbora.enums.Pago;
import school.sptech.vannbora.repository.RegistroFaturaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistroFaturaServiceTest {

    @Mock
    private RegistroFaturaRepository registroFaturaRepository;

    @Mock
    private FaturaService faturaService;

    @InjectMocks
    private RegistroFaturaService registroFaturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvar_deveSalvarComSucesso() {
        RegistroFatura registro = new RegistroFatura();
        registro.setPago(Pago.PAGO);
        Fatura fatura = new Fatura();

        when(faturaService.buscarPorId(1)).thenReturn(fatura);
        when(registroFaturaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        RegistroFatura salvo = registroFaturaService.salvar(registro, 1);

        assertNotNull(salvo.getFatura());
        assertEquals(Pago.PAGO, salvo.getPago());
        verify(registroFaturaRepository).save(registro);
    }

    @Test
    void atualizar_deveAtualizarRegistroComSucesso() {
        RegistroFatura existente = new RegistroFatura();
        existente.setPago(Pago.NAO_PAGO);

        RegistroFatura atualizado = new RegistroFatura();
        atualizado.setPago(Pago.PAGO);

        when(registroFaturaRepository.findById(1)).thenReturn(Optional.of(existente));
        when(registroFaturaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        RegistroFatura resultado = registroFaturaService.atualizar(1, atualizado);

        assertEquals(Pago.PAGO, resultado.getPago());
        verify(registroFaturaRepository).save(existente);
    }

    @Test
    void listarPorFaturaId_deveRetornarLista() {
        List<RegistroFatura> lista = List.of(new RegistroFatura(), new RegistroFatura());

        when(registroFaturaRepository.findAllByFaturaId(1)).thenReturn(lista);

        List<RegistroFatura> resultado = registroFaturaService.listarPorFaturaId(1);

        assertEquals(2, resultado.size());
        verify(registroFaturaRepository).findAllByFaturaId(1);
    }

    @Test
    void somarValorPorPeriodo_deveRetornarSoma() {
        LocalDate inicio = LocalDate.now().minusDays(10);
        LocalDate fim = LocalDate.now();
        BigDecimal esperado = BigDecimal.valueOf(500.00);

        when(registroFaturaRepository.findSumFaturaValorByFaturaResponsavelDependenteDependenteProprietarioServicoIdAndDataPagamentoBetween(1, inicio, fim))
                .thenReturn(esperado);

        BigDecimal resultado = registroFaturaService.somarValorPorPeriodo(1, inicio, fim);

        assertEquals(esperado, resultado);
    }

    @Test
    void calcularRecebimentoRealDoAnoAtualPorMes_deveRetornarMapa() {
        List<Object[]> mockResults = List.of(new Object[]{1, 100.0}, new Object[]{2, 200.0});

        when(registroFaturaRepository.findRecebimentoRealDoAnoAtualPorMes(1, Pago.PAGO)).thenReturn(mockResults);

        Map<Integer, BigDecimal> resultado = registroFaturaService.calcularRecebimentoRealDoAnoAtualPorMes(1, Pago.PAGO);

        assertEquals(BigDecimal.valueOf(100.0), resultado.get(1));
        assertEquals(BigDecimal.valueOf(200.0), resultado.get(2));
    }

    @Test
    void contarPagamentosPendentesPorEscolaId_deveRetornarContagem() {
        when(registroFaturaRepository.countByFaturaResponsavelDependenteDependenteEscolaIdAndPago(5, Pago.NAO_PAGO))
                .thenReturn(3);

        int resultado = registroFaturaService.contarPagamentosPendentesPorEscolaId(5);

        assertEquals(3, resultado);
    }
}
