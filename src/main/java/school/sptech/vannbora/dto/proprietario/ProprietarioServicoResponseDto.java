package school.sptech.vannbora.dto.proprietario;

import lombok.Builder;
import school.sptech.vannbora.enums.ProprietarioServicoRole;

@Builder
public record ProprietarioServicoResponseDto(
        Integer id,
        String nome,
        String email,
        String cpf,
        ProprietarioServicoRole role,
        String token) {
}
