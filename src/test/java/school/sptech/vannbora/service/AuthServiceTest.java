package school.sptech.vannbora.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.repository.ProprietarioServicoRepository;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private ProprietarioServicoRepository repository;

    @Test
    void deveRetornarUserDetailsQuandoUsuarioForEncontrado() {
        // Arrange
        String email = "usuario@exemplo.com";
        ProprietarioServico proprietarioMock = ProprietarioServico.builder()
                .email(email)
                .senha("senha123")
                .build();

        when(repository.findByEmail(email)).thenReturn(proprietarioMock);


        UserDetails userDetails = authService.loadUserByUsername(email);


        assertNotNull(userDetails, "O UserDetails retornado não deve ser nulo");
        assertEquals(email, userDetails.getUsername(), "O email deve ser igual ao mockado");
        assertEquals(proprietarioMock.getSenha(), userDetails.getPassword(), "A senha deve ser igual ao mockado");
        verify(repository, times(1)).findByEmail(email);
    }

    @Test
    void deveLancarUsernameNotFoundExceptionQuandoUsuarioNaoForEncontrado() {

        String email = "naoexistente@exemplo.com";
        when(repository.findByEmail(email)).thenReturn(null);


        UsernameNotFoundException exception = assertThrows(
                UsernameNotFoundException.class,
                () -> authService.loadUserByUsername(email),
                "Deve lançar UsernameNotFoundException se o usuário não for encontrado"
        );

        assertEquals("Usuário não encontrado", exception.getMessage(), "A mensagem da exceção deve ser clara");
        verify(repository, times(1)).findByEmail(email);
    }
}
