package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.trajetoDependente.TrajetoDependenteRequestDto;
import school.sptech.vannbora.dto.trajetoDependente.TrajetoDependenteResponseDto;
import school.sptech.vannbora.entidade.TrajetoDependente;

public class TrajetoDependenteMapper {

    public static TrajetoDependenteResponseDto toResponseDto (TrajetoDependente trajetoDependente) {
        return TrajetoDependenteResponseDto.builder()
                .id(trajetoDependente.getId())
                .responsavelDependente(ResponsavelDependenteMapper.toResponseDto(trajetoDependente.getResponsavelDependente()))
                .ordem(trajetoDependente.getOrdem())
                .build();
    }

    public static TrajetoDependente toTrajetoDependente(TrajetoDependenteRequestDto dto){
        if(dto == null){
            return null;
        }

        return TrajetoDependente.builder()
                .id(dto.trajetoDependenteId())
                .responsavelDependente(ResponsavelDependenteMapper.toEntity(dto.responsavelDependenteRequestDto()))
                .build();
    }
}
