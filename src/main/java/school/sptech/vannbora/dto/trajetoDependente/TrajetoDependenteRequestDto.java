package school.sptech.vannbora.dto.trajetoDependente;

import lombok.Builder;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteRequestDto;

@Builder
public record TrajetoDependenteRequestDto (
        ResponsavelDependenteRequestDto responsavelDependenteRequestDto,
        Integer trajetoDependenteId
){}
