package school.sptech.vannbora.dto.trajetoDependente;

import lombok.Builder;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteResponseDto;

@Builder
public record TrajetoDependenteResponseDto(
        Integer id,
        ResponsavelDependenteResponseDto responsavelDependente
) {
}
