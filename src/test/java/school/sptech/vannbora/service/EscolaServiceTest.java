package school.sptech.vannbora.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.Escola;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.repository.EscolaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EscolaServiceTest {

    private EscolaRepository escolaRepository;
    private EnderecoService enderecoService;
    private ProprietarioServicoService proprietarioServicoService;
    private RegistroFaturaService registroFaturaService;

    private EscolaService escolaService;

    @BeforeEach
    void setUp() {
        escolaRepository = mock(EscolaRepository.class);
        enderecoService = mock(EnderecoService.class);
        proprietarioServicoService = mock(ProprietarioServicoService.class);
        registroFaturaService = mock(RegistroFaturaService.class);

        escolaService = new EscolaService(
                escolaRepository,
                enderecoService,
                proprietarioServicoService,
                registroFaturaService
        );
    }

    @Test
    void testCadastrarComEnderecoIdEProprietarioId_DeveRetornarEscolaSalva() {
        // Arrange
        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setCep("12345-678");
        endereco.setBairro("Centro");
        endereco.setCidade("São Paulo");
        endereco.setLogradouro("Rua A");
        endereco.setNumero("123");

        ProprietarioServico proprietario = new ProprietarioServico();
        proprietario.setId(10);

        Escola escolaParaCadastrar = new Escola();
        escolaParaCadastrar.setNome("Escola Exemplo");
        escolaParaCadastrar.setTelefone("11999999999");
        escolaParaCadastrar.setTelefoneResponsavel("11988888888");
        escolaParaCadastrar.setNomeResponsavel("João");

        when(enderecoService.buscarPorId(1)).thenReturn(endereco);
        when(proprietarioServicoService.buscarPorId(10)).thenReturn(proprietario);
        when(escolaRepository.save(any(Escola.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Escola escolaSalva = escolaService.cadastrar(escolaParaCadastrar, 1, 10);

        // Assert
        assertNotNull(escolaSalva);
        assertEquals("Escola Exemplo", escolaSalva.getNome());
        assertEquals("João", escolaSalva.getNomeResponsavel());
        assertEquals(endereco, escolaSalva.getEndereco());
        assertEquals(proprietario, escolaSalva.getProprietarioServico());

        verify(enderecoService).buscarPorId(1);
        verify(proprietarioServicoService).buscarPorId(10);
        verify(escolaRepository).save(any(Escola.class));
    }
}
