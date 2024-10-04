package school.sptech.vannbora.dto.proprietario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProprietarioServicoLoginDto(
        @NotBlank(message = "Insira um email.") @Email(message = "Digite um email válido.") String email,
        @NotNull(message = "A senha não pode ser nula.") @Size(min = 8, message = "A senha deve ter no mínimo 2 carcteres.") String senha) {
}
