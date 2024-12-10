package school.sptech.vannbora.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.enums.ProprietarioServicoRole;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.ProprietarioServicoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProprietarioServicoServiceTest {

    @InjectMocks
    private ProprietarioServicoService proprietarioServicoService;

    @Mock
    private ProprietarioServicoRepository proprietarioServicoRepository;

    @Test
    void listar() {
        when(proprietarioServicoRepository.findAll()).thenReturn(new ArrayList<>());

        List<ProprietarioServico> proprietarios = proprietarioServicoService.listar();

        assertNotNull(proprietarios);
        assertTrue(proprietarios.isEmpty());
        verify(proprietarioServicoRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_Sucesso() {
        ProprietarioServico proprietarioMock = new ProprietarioServico();
        proprietarioMock.setId(1);
        proprietarioMock.setNome("João");

        when(proprietarioServicoRepository.findById(1)).thenReturn(Optional.of(proprietarioMock));

        ProprietarioServico proprietario = proprietarioServicoService.buscarPorId(1);

        assertNotNull(proprietario);
        assertEquals(1, proprietario.getId());
        assertEquals("João", proprietario.getNome());
        verify(proprietarioServicoRepository, times(1)).findById(1);
    }

    @Test
    void buscarPorId_NaoEncontrado() {
        when(proprietarioServicoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> proprietarioServicoService.buscarPorId(1));

        verify(proprietarioServicoRepository, times(1)).findById(1);
    }

    @Test
    void atualizar_Sucesso() {
        ProprietarioServico proprietarioAtualMock = new ProprietarioServico();
        proprietarioAtualMock.setId(1);
        proprietarioAtualMock.setNome("João");

        ProprietarioServico proprietarioNovo = new ProprietarioServico();
        proprietarioNovo.setNome("Carlos");
        proprietarioNovo.setEmail("carlos@email.com");
        proprietarioNovo.setCpf("123.456.789-00");
        proprietarioNovo.setSenha("senha123");
        proprietarioNovo.setRole(ProprietarioServicoRole.USER);

        when(proprietarioServicoRepository.findById(1)).thenReturn(Optional.of(proprietarioAtualMock));
        when(proprietarioServicoRepository.save(any(ProprietarioServico.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ProprietarioServico proprietarioAtualizado = proprietarioServicoService.atualizar(1, proprietarioNovo);

        assertNotNull(proprietarioAtualizado);
        assertEquals("Carlos", proprietarioAtualizado.getNome());
        assertEquals("carlos@email.com", proprietarioAtualizado.getEmail());
        assertEquals("123.456.789-00", proprietarioAtualizado.getCpf());
        assertEquals("senha123", proprietarioAtualizado.getSenha());
        assertEquals(ProprietarioServicoRole.USER, proprietarioAtualizado.getRole());
        verify(proprietarioServicoRepository, times(1)).findById(1);
        verify(proprietarioServicoRepository, times(1)).save(any(ProprietarioServico.class));
    }

    @Test
    void atualizar_NaoEncontrado() {
        ProprietarioServico proprietarioNovo = new ProprietarioServico();
        proprietarioNovo.setNome("Carlos");

        when(proprietarioServicoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> proprietarioServicoService.atualizar(1, proprietarioNovo));

        verify(proprietarioServicoRepository, times(1)).findById(1);
        verify(proprietarioServicoRepository, never()).save(any(ProprietarioServico.class));
    }

    @Test
    void deletar_Sucesso() {
        ProprietarioServico proprietarioMock = new ProprietarioServico();
        proprietarioMock.setId(1);

        when(proprietarioServicoRepository.findById(1)).thenReturn(Optional.of(proprietarioMock));

        proprietarioServicoService.deletar(1);

        verify(proprietarioServicoRepository, times(1)).deleteById(1);
    }

    @Test
    void deletar_NaoEncontrado() {
        when(proprietarioServicoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> proprietarioServicoService.deletar(1));

        verify(proprietarioServicoRepository, times(1)).findById(1);
        verify(proprietarioServicoRepository, never()).deleteById(1);
    }
}
