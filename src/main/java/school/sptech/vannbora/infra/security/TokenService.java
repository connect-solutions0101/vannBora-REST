package school.sptech.vannbora.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import school.sptech.vannbora.entidade.ProprietarioServico;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${API_SECURITY_TOKEN_SECRET}")
    private String chaveSecreta;

    public String gerarToken(ProprietarioServico proprietario){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(chaveSecreta);
            String token = JWT.create()
                    .withIssuer("vannbora-api")
                    .withSubject(proprietario.getEmail())
                    .withExpiresAt(gerarDataDeExpiracao())
                    .sign(algoritmo);

            return token;
        } catch(JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validarToken(String token){
        try{
            Algorithm algoritmo = Algorithm.HMAC256(chaveSecreta);
            return JWT.require(algoritmo)
                    .withIssuer("vannbora-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch(JWTVerificationException exception){
            return "";
        }
    }

    private Instant gerarDataDeExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
