package school.sptech.vannbora.dto.responsaveldependente;

import lombok.Builder;
import school.sptech.vannbora.dto.dependente.DependenteEscolaResponsaveisResponseDto;
import school.sptech.vannbora.dto.responsavel.ResponsavelResponseDto;
import school.sptech.vannbora.enums.TipoResponsavel;

@Builder
public record ResponsavelDependenteEscolaResponseDto(
    int responsavelId,
    int dependenteId,
    TipoResponsavel tipoResponsavel,
    ResponsavelResponseDto responsavel,
    DependenteEscolaResponsaveisResponseDto dependente
) {
    
}
