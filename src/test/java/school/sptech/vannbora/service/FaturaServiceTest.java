package school.sptech.vannbora.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.vannbora.entidade.Fatura;
import school.sptech.vannbora.entidade.ResponsavelDependente;
import school.sptech.vannbora.enums.Pago;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.FaturaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FaturaServiceTest {

    @InjectMocks
    private FaturaService faturaService;

    @Mock
    private FaturaRepository faturaRepository;

    @Mock
    private ResponsavelDependenteService responsavelDependenteService;

    @Test
    void salvar_deveSalvarFaturaComSucesso() {
        Fatura faturaMock = new Fatura();
        faturaMock.setValor(100.0);

        ResponsavelDependente responsavelDependenteMock = new ResponsavelDependente();
        when(responsavelDependenteService.buscarPorId(anyInt(), anyInt()))
                .thenReturn(responsavelDependenteMock);

        when(faturaRepository.save(any(Fatura.class))).thenAnswer(invocation -> {
            Fatura fatura = invocation.getArgument(0, Fatura.class);
            fatura.setId(1); // Simula o comportamento de persistência
            return fatura;
        });

        Fatura faturaSalva = faturaService.salvar(faturaMock, 1, 1);

        assertNotNull(faturaSalva);
        assertEquals(1, faturaSalva.getId());
        assertEquals(100.0, faturaSalva.getValor());
        assertNotNull(faturaSalva.getResponsavelDependente());
        verify(responsavelDependenteService, times(1)).buscarPorId(1, 1);
        verify(faturaRepository, times(1)).save(faturaMock);
    }

    @Test
    void listar_deveRetornarListaVaziaQuandoNaoExistemFaturas() {
        when(faturaRepository.findAll()).thenReturn(new ArrayList<>());

        List<Fatura> faturas = faturaService.listar();

        assertNotNull(faturas);
        assertTrue(faturas.isEmpty());
        verify(faturaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_deveRetornarFaturaQuandoEncontrada() {
        Fatura faturaMock = new Fatura();
        faturaMock.setId(1);

        when(faturaRepository.findById(1)).thenReturn(Optional.of(faturaMock));

        Fatura fatura = faturaService.buscarPorId(1);

        assertNotNull(fatura);
        assertEquals(1, fatura.getId());
        verify(faturaRepository, times(1)).findById(1);
    }

    @Test
    void buscarPorId_deveLancarExcecaoQuandoNaoEncontrada() {
        when(faturaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> faturaService.buscarPorId(1));
        verify(faturaRepository, times(1)).findById(1);
    }

    @Test
    void listarPorIdDependente_deveRetornarFaturasDoDependente() {
        Fatura faturaMock = new Fatura();
        faturaMock.setId(1);

        when(faturaRepository.findAllByResponsavelDependenteDependenteId(1))
                .thenReturn(List.of(faturaMock));

        List<Fatura> faturas = faturaService.listarPorIdDependente(1);

        assertNotNull(faturas);
        assertFalse(faturas.isEmpty());
        assertEquals(1, faturas.size());
        assertEquals(1, faturas.get(0).getId());
        verify(faturaRepository, times(1)).findAllByResponsavelDependenteDependenteId(1);
    }

    // @Test
    // void contarPorIdDependenteFaturasPendentes_deveRetornarContagemCorreta() {
    //     when(faturaRepository.countByResponsavelDependenteDependenteIdAndPagoEqualsAndDataVencimentoBetween(
    //             eq(1), eq(Pago.NAO_PAGO), any(LocalDate.class), any(LocalDate.class)))
    //             .thenReturn(5);

    //     int count = faturaService.contarPorIdDependenteFaturasPendentes(1);

    //     assertEquals(5, count);
    //     verify(faturaRepository, times(1)).countByResponsavelDependenteDependenteIdAndPagoEqualsAndDataVencimentoBetween(
    //             eq(1), eq(Pago.NAO_PAGO), any(LocalDate.class), any(LocalDate.class));
    // }

    @Test
    void listarPorIdResponsavel_deveRetornarFaturasDoResponsavel() {
        Fatura faturaMock = new Fatura();
        faturaMock.setId(1);

        when(faturaRepository.findAllByResponsavelDependenteResponsavelId(1))
                .thenReturn(List.of(faturaMock));

        List<Fatura> faturas = faturaService.listarPorIdResponsavel(1);

        assertNotNull(faturas);
        assertFalse(faturas.isEmpty());
        assertEquals(1, faturas.size());
        verify(faturaRepository, times(1)).findAllByResponsavelDependenteResponsavelId(1);
    }

    @Test
    void atualizar_deveAtualizarFaturaComSucesso() {
        Fatura faturaMock = new Fatura();
        faturaMock.setId(1);
        faturaMock.setValor(100.0);

        Fatura faturaAtualizada = new Fatura();
        faturaAtualizada.setId(1);
        faturaAtualizada.setValor(200.0);

        ResponsavelDependente responsavelDependenteMock = new ResponsavelDependente();
        when(faturaRepository.findById(1)).thenReturn(Optional.of(faturaMock));
        when(responsavelDependenteService.buscarPorId(1, 1)).thenReturn(responsavelDependenteMock);
        when(faturaRepository.save(any(Fatura.class))).thenReturn(faturaAtualizada);

        Fatura fatura = faturaService.atualizar(faturaAtualizada, 1, 1);

        assertNotNull(fatura);
        assertEquals(200.0, fatura.getValor());
        verify(faturaRepository, times(1)).findById(1);
        verify(faturaRepository, times(1)).save(faturaMock);
        verify(responsavelDependenteService, times(1)).buscarPorId(1, 1);
    }

    @Test
    void deletar_deveDeletarFaturaComSucesso() {
        Fatura faturaMock = new Fatura();
        faturaMock.setId(1);

        when(faturaRepository.findById(1)).thenReturn(Optional.of(faturaMock));

        faturaService.deletar(1);

        verify(faturaRepository, times(1)).deleteById(1);
    }

    @Test
    void deletar_deveLancarExcecaoQuandoFaturaNaoEncontrada() {
        when(faturaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> faturaService.deletar(1));
        verify(faturaRepository, times(1)).findById(1);
    }
}
