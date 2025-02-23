package school.sptech.vannbora.dto.trajetoDependente;

import lombok.Builder;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteResponseDto;
import school.sptech.vannbora.entidade.TrajetoDependente.TrajetoDependenteId;

@Builder
public record TrajetoDependenteResponseDto(
        TrajetoDependenteId id,
        ResponsavelDependenteResponseDto responsavelDependente
) {
}
