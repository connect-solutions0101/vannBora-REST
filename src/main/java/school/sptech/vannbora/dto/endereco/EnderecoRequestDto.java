package school.sptech.vannbora.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import school.sptech.vannbora.enums.UfEnum;

public record EnderecoRequestDto(
        @NotBlank(message = "Cep vazio ou nulo.") @Size(min = 7, max = 8, message = "Cep deve ter entre 7 e 8 caracteres.") String cep,
        @NotBlank(message = "Logradouro vazio ou nulo.") String logradouro,
        @NotBlank(message = "Número vazio ou nulo.") String numero,
        @NotBlank(message = "Cidade vazio ou nulo.") String cidade,
        @NotNull(message = "UF nulo.") UfEnum uf
) {
}
