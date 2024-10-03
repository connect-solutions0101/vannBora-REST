package school.sptech.vannbora.dto.proprietario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.vannbora.enums.ProprietarioServicoRole;

public record ProprietarioServicoRequestDto(
        @Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres") String nome,
        @NotBlank(message = "Insira um email") @Email(message = "Digite um email válido") String email,
        @Size(min = 8, message = "A senha deve ter no mínimo 2 carcteres") String senha,
        @NotNull(message = "O usuário deve ter uma role") ProprietarioServicoRole role) {
}