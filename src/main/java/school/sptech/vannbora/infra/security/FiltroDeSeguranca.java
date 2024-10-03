package school.sptech.vannbora.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import school.sptech.vannbora.repository.ProprietarioServicoRepository;

import java.io.IOException;

@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProprietarioServicoRepository proprietarioServicoRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);
        if(token != null){
            var login = tokenService.validarToken(token);
            UserDetails proprietario = proprietarioServicoRepository.findByEmail(login);

            var auth = new UsernamePasswordAuthenticationToken(proprietario, null, proprietario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null){
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
