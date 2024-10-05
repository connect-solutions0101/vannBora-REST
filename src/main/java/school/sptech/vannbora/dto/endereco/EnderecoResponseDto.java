package school.sptech.vannbora.dto.endereco;

import school.sptech.vannbora.enums.UfEnum;

public record EnderecoResponseDto(
        String cep,
        String logradouro,
        String numero,
        String cidade,
        UfEnum uf
) {
}
