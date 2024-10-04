package school.sptech.vannbora.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoLoginDto;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoRequestDto;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoResponseDto;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.infra.security.TokenService;
import school.sptech.vannbora.repository.ProprietarioServicoRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ProprietarioServicoRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid ProprietarioServicoLoginDto proprietario){
        var usernamePassword = new UsernamePasswordAuthenticationToken(proprietario.email(), proprietario.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((ProprietarioServico) auth.getPrincipal());

        return ResponseEntity.ok(
                new ProprietarioServicoResponseDto(
                ((ProprietarioServico) auth.getPrincipal()).getId(),
                ((ProprietarioServico) auth.getPrincipal()).getNome(),
                ((ProprietarioServico) auth.getPrincipal()).getEmail(),
                ((ProprietarioServico) auth.getPrincipal()).getRole(),
                token
        ));
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid ProprietarioServicoRequestDto proprietario){
        if(this.repository.findByEmail(proprietario.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(proprietario.senha());
        ProprietarioServico novoProprietario = new ProprietarioServico(
                proprietario.nome(),
                proprietario.email(),
                senhaCriptografada,
                proprietario.role()
        );
        this.repository.save(novoProprietario);

        return ResponseEntity.ok().build();
    }
}
