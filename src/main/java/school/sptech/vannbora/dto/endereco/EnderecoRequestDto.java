package school.sptech.vannbora.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDto(
        @NotBlank(message = "Cep vazio ou nulo.") @Size(min = 8, max = 8, message = "Cep deve ter 8 caracteres.") String cep,
        @NotBlank(message = "Logradouro vazio ou nulo.") String logradouro,
        @NotBlank(message = "Bairro vazio ou nulo.") String bairro,
        @NotBlank(message = "Cidade vazio ou nulo.") String cidade,
        String pontoReferencia,
        @NotBlank(message = "Número vazio ou nulo.") String numero
) {
}
