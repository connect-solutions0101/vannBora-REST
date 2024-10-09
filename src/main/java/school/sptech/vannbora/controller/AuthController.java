package school.sptech.vannbora.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoLoginDto;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoRequestDto;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoResponseDto;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.infra.security.TokenService;
import school.sptech.vannbora.repository.ProprietarioServicoRepository;
import school.sptech.vannbora.service.EnderecoService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final ProprietarioServicoRepository repository;
    private final EnderecoService enderecoService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid ProprietarioServicoLoginDto proprietario){
        var usernamePassword = new UsernamePasswordAuthenticationToken(proprietario.email(), proprietario.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((ProprietarioServico) auth.getPrincipal());

        return ResponseEntity.ok(
                ProprietarioServicoResponseDto.builder()
                        .id(((ProprietarioServico) auth.getPrincipal()).getId())
                        .nome(((ProprietarioServico) auth.getPrincipal()).getNome())
                        .email(((ProprietarioServico) auth.getPrincipal()).getEmail())
                        .cpf(((ProprietarioServico) auth.getPrincipal()).getCpf())
                        .role(((ProprietarioServico) auth.getPrincipal()).getRole())
                        .token(token)
                        .build()
        );
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid ProprietarioServicoRequestDto proprietario){
        if(this.repository.findByEmail(proprietario.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(proprietario.senha());
        Endereco endereco = enderecoService.buscarPorId(proprietario.enderecoId());
        ProprietarioServico novoProprietario = new ProprietarioServico(
                proprietario.nome(),
                proprietario.email(),
                proprietario.cpf(),
                senhaCriptografada,
                proprietario.role(),
                endereco
        );
        this.repository.save(novoProprietario);

        return ResponseEntity.ok().build();
    }
}
