package school.sptech.vannbora.dto.responsavel;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ResponsavelRequestDto(
    @Size(min = 2, message = "O nome deve ter no mínimo 2 caracteres") 
    String nome,

    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "O telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX") 
    String telefone,

    @Size(min = 2, message = "O parentesco deve ter no mínimo 2 caracteres")
    String parentesco,

    int enderecoId,

    int proprietarioServicoId
) {
} 
