package school.sptech.vannbora.dto.responsavel;

import lombok.Builder;
import school.sptech.vannbora.dto.endereco.EnderecoResponseDto;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoResponseDto;

@Builder
public record ResponsavelResponseDto(
        int id,
        String nome,
        String telefone,
        String parentesco,
        EnderecoResponseDto endereco,
        ProprietarioServicoResponseDto proprietarioServico
) {}
