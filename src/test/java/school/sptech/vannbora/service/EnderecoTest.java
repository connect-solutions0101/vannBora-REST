package school.sptech.vannbora.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.exception.RegistroNaoEncontradoException;
import school.sptech.vannbora.repository.EnderecoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnderecoServiceTest {

    private EnderecoRepository enderecoRepository;
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        enderecoRepository = Mockito.mock(EnderecoRepository.class);
        enderecoService = new EnderecoService();
    }

    @Test
    void deveListarEnderecos() {
        Endereco e1 = new Endereco();
        Endereco e2 = new Endereco();
        when(enderecoRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Endereco> resultado = enderecoService.listar();

        assertEquals(2, resultado.size());
        verify(enderecoRepository, times(1)).findAll();
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        when(enderecoRepository.findById(1)).thenReturn(Optional.of(endereco));

        Endereco resultado = enderecoService.buscarPorId(1);

        assertEquals(1, resultado.getId());
        verify(enderecoRepository).findById(1);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExiste() {
        when(enderecoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> enderecoService.buscarPorId(99));
    }

    @Test
    void deveBuscarPorCepComSucesso() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        when(enderecoRepository.findByCep("12345-678")).thenReturn(Optional.of(endereco));

        Endereco resultado = enderecoService.buscarPorCep("12345-678");

        assertEquals("12345-678", resultado.getCep());
    }

    @Test
    void deveLancarExcecaoQuandoCepNaoExiste() {
        when(enderecoRepository.findByCep("00000-000")).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> enderecoService.buscarPorCep("00000-000"));
    }

    @Test
    void deveCadastrarEndereco() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        Endereco resultado = enderecoService.cadastrar(endereco);

        assertEquals(endereco, resultado);
    }

    @Test
    void deveAtualizarEnderecoComSucesso() {
        Endereco existente = new Endereco();
        existente.setId(1);
        existente.setCep("11111-111");

        Endereco novo = new Endereco();
        novo.setCep("22222-222");
        novo.setLogradouro("Rua Nova");
        novo.setBairro("Bairro Novo");
        novo.setCidade("Cidade Nova");
        novo.setPontoReferencia("Referência");
        novo.setNumero("123");

        when(enderecoRepository.findById(1)).thenReturn(Optional.of(existente));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(existente);

        Endereco resultado = enderecoService.atualizar(1, novo);

        assertEquals("22222-222", resultado.getCep());
        verify(enderecoRepository).save(existente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarEnderecoInexistente() {
        Endereco novo = new Endereco();
        when(enderecoRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> enderecoService.atualizar(999, novo));
    }

    @Test
    void deveDeletarEnderecoComSucesso() {
        Endereco endereco = new Endereco();
        when(enderecoRepository.findById(1)).thenReturn(Optional.of(endereco));

        enderecoService.deletar(1);

        verify(enderecoRepository).deleteById(1);
    }

    @Test
    void deveLancarExcecaoAoDeletarEnderecoInexistente() {
        when(enderecoRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RegistroNaoEncontradoException.class, () -> enderecoService.deletar(99));
    }
}
