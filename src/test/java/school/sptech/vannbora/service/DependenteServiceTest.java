package school.sptech.vannbora.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.DependenteRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DependenteServiceTest {

    @InjectMocks
    private DependenteService dependenteService;

    @Mock
    private DependenteRepository dependenteRepository;

    @Mock
    private EscolaService escolaService;

    @Mock
    private ProprietarioServicoService proprietarioServicoService;

    @Test
    void listar() {
        when(dependenteRepository.findAll()).thenReturn(new ArrayList<>());

        List<Dependente> dependentes = dependenteService.listar();

        assertNotNull(dependentes);
        assertTrue(dependentes.isEmpty());
        verify(dependenteRepository, times(1)).findAll();
    }

    @Test
    void listarFull() {
        Dependente dependente = new Dependente();
        dependente.setId(1);

        when(dependenteRepository.findAllByProprietarioServicoIdAndNomeContaining(1, null)).thenReturn(List.of(dependente));

        List<Dependente> dependentes = dependenteService.listarFull(1, null);

        assertNotNull(dependentes);
        assertFalse(dependentes.isEmpty());
        assertEquals(1, dependentes.size());
        verify(dependenteRepository, times(1)).findAllByProprietarioServicoIdAndNomeContaining(1, null);
    }

    @Test
    void salvar() {
        Dependente dependenteMock = new Dependente();
        dependenteMock.setNome("João");
        dependenteMock.setId(1);

        Escola escolaMock = new Escola();
        escolaMock.setId(1);

        ProprietarioServico proprietarioMock = new ProprietarioServico();
        proprietarioMock.setId(1);

        when(escolaService.buscarPorId(1)).thenReturn(escolaMock);
        when(proprietarioServicoService.buscarPorId(1)).thenReturn(proprietarioMock);
        when(dependenteRepository.save(any(Dependente.class))).thenAnswer(invocation -> {
            Dependente dependente = invocation.getArgument(0, Dependente.class);
            dependente.setId(1);
            dependente.setDataCadastro(LocalDate.now());
            return dependente;
        });

        Dependente dependenteSalvo = dependenteService.salvar(dependenteMock, 1, 1);

        assertNotNull(dependenteSalvo);
        assertEquals(1, dependenteSalvo.getId());
        assertEquals("João", dependenteSalvo.getNome());
        assertNotNull(dependenteSalvo.getDataCadastro());
        verify(escolaService, times(1)).buscarPorId(1);
        verify(proprietarioServicoService, times(1)).buscarPorId(1);
        verify(dependenteRepository, times(1)).save(any(Dependente.class));
    }

    @Test
    void atualizar() {
        Dependente dependenteMock = new Dependente();
        dependenteMock.setId(1);
        dependenteMock.setNome("João");

        Dependente dependenteAtualizado = new Dependente();
        dependenteAtualizado.setNome("Carlos");

        Escola escolaMock = new Escola();
        escolaMock.setId(1);

        ProprietarioServico proprietarioMock = new ProprietarioServico();
        proprietarioMock.setId(1);

        when(dependenteRepository.findById(1)).thenReturn(Optional.of(dependenteMock));
        when(escolaService.buscarPorId(1)).thenReturn(escolaMock);
        when(proprietarioServicoService.buscarPorId(1)).thenReturn(proprietarioMock);
        when(dependenteRepository.save(any(Dependente.class))).thenAnswer(invocation -> {
            Dependente dependente = invocation.getArgument(0, Dependente.class);
            dependente.setNome("Carlos");
            return dependente;
        });

        Dependente dependente = dependenteService.atualizar(1, dependenteAtualizado, 1, 1);

        assertNotNull(dependente);
        assertEquals("Carlos", dependente.getNome());
        verify(dependenteRepository, times(1)).findById(1);
        verify(escolaService, times(1)).buscarPorId(1);
        verify(proprietarioServicoService, times(1)).buscarPorId(1);
        verify(dependenteRepository, times(1)).save(any(Dependente.class));
    }

    @Test
    void deletar() {
        Dependente dependenteMock = new Dependente();
        dependenteMock.setId(1);

        when(dependenteRepository.findById(1)).thenReturn(Optional.of(dependenteMock));

        dependenteService.deletar(1);

        verify(dependenteRepository, times(1)).deleteById(1);

        when(dependenteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> dependenteService.deletar(1));
    }

    @Test
    void buscarPorId() {
        Dependente dependenteMock = new Dependente();
        dependenteMock.setId(1);

        when(dependenteRepository.findById(1)).thenReturn(Optional.of(dependenteMock));

        Dependente dependente = dependenteService.buscarPorId(1);

        assertNotNull(dependente);
        assertEquals(1, dependente.getId());
        verify(dependenteRepository, times(1)).findById(1);

        when(dependenteRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> dependenteService.buscarPorId(2));
    }
}
