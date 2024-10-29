package school.sptech.vannbora.dto.escola;

import school.sptech.vannbora.dto.endereco.EnderecoResponseDto;

public record EscolaResponseDto(
        int id,
        String nome,
        String nomeResponsavel,
        String telefone,
        String telefoneResponsavel,
        EnderecoResponseDto endereco
) {
}
