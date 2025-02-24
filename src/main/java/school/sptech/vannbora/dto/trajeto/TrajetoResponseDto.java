package school.sptech.vannbora.dto.trajeto;

import lombok.Builder;
import school.sptech.vannbora.dto.trajetoDependente.TrajetoDependenteResponseDto;
import school.sptech.vannbora.enums.Periodo;

import java.util.List;

@Builder
public record TrajetoResponseDto(
        Integer id,
        String nome,
        Periodo periodo,
        List<TrajetoDependenteResponseDto> trajetoDependentes
) {
}
