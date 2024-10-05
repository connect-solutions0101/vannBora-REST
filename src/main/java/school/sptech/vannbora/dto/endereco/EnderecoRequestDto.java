package school.sptech.vannbora.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import school.sptech.vannbora.enums.UfEnum;

public record EnderecoRequestDto(
        @NotBlank(message = "Cep vazio ou nulo.") String cep,
        @NotBlank(message = "Logradouro vazio ou nulo.") String logradouro,
        @NotBlank(message = "Número vazio ou nulo.") String numero,
        @NotBlank(message = "Cidade vazio ou nulo.") String cidade,
        @NotBlank(message = "UF vazio ou nulo.") @Size(min = 2, max = 2, message = "Número de caracteres inválido.") UfEnum uf
) {
}
