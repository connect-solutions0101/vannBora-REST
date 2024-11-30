package school.sptech.vannbora.dto.responsaveldependente;

import lombok.Builder;
import school.sptech.vannbora.enums.TipoResponsavel;
import school.sptech.vannbora.dto.responsavel.ResponsavelResponseDto;

@Builder
public record ResponsavelOnlyResponseDto(
    Integer responsavelId,
    Integer dependenteId,
    TipoResponsavel tipoResponsavel,
    ResponsavelResponseDto responsavel
) {
    
}
