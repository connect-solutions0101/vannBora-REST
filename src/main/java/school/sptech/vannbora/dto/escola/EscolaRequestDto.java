package school.sptech.vannbora.dto.escola;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EscolaRequestDto(
        @Size(min = 5, message = "O nome da escola deve ter no mínimo 5 caracteres")
        String nome,

        @Size(min = 2, message = "O nome do responsável pela escola deve ter no mínimo 2 caracteres")
        String nomeResponsavel,

        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
        String telefone,

        @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
        String telefoneResponsavel,

        @NotNull
        int enderecoId
) {
}
