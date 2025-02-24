package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.trajetoDependente.TrajetoDependenteResponseDto;
import school.sptech.vannbora.entidade.TrajetoDependente;

public class TrajetoDependenteMapper {

    public static TrajetoDependenteResponseDto toResponseDto (TrajetoDependente trajetoDependente) {
        return TrajetoDependenteResponseDto.builder()
                .id(trajetoDependente.getId())
                .responsavelDependente(ResponsavelDependenteMapper.toResponseDto(trajetoDependente.getResponsavelDependente()))
                .build();
    }
}
