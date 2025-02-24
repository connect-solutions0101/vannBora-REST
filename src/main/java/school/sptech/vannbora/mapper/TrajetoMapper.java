package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.trajeto.TrajetoResponseDto;
import school.sptech.vannbora.entidade.Trajeto;

public class TrajetoMapper {

    public static TrajetoResponseDto toResponseDto (Trajeto trajeto) {
        return TrajetoResponseDto.builder()
                .id(trajeto.getId())
                .nome(trajeto.getNome())
                .periodo(trajeto.getPeriodo())
                .trajetoDependentes(trajeto.getTrajetoDependentes().stream()
                        .map(TrajetoDependenteMapper::toResponseDto)
                        .toList())
                .build();
    }
}
