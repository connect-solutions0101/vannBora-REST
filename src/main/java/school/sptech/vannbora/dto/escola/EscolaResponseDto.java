package school.sptech.vannbora.dto.escola;

import school.sptech.vannbora.dto.endereco.EnderecoResponseDto;

public record EscolaResponseDto(
        String nome,
        String nomeResponsavel,
        String telefone,
        String telefoneResponsavel,
        EnderecoResponseDto endereco
) {
}
