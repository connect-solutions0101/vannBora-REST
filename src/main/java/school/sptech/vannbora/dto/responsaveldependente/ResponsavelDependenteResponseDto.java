package school.sptech.vannbora.dto.responsaveldependente;

import lombok.Builder;
import school.sptech.vannbora.dto.dependente.DependenteResponseDto;
import school.sptech.vannbora.dto.responsavel.ResponsavelResponseDto;
import school.sptech.vannbora.enums.TipoResponsavel;

@Builder
public record ResponsavelDependenteResponseDto(
    int responsavelId,
    int dependenteId,
    TipoResponsavel tipoResponsavel,
    ResponsavelResponseDto responsavel,
    DependenteResponseDto dependente
) {
    
}
