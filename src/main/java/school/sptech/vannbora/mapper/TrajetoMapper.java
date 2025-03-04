package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.trajeto.TrajetoRequestDto;
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

    public static Trajeto toTrajeto(TrajetoRequestDto dto){
        if(dto == null){
            return null;
        }

        return Trajeto.builder()
                .nome(dto.nome())
                .periodo(dto.periodo())
                .trajetoDependentes(dto.trajetoDependentes())
                .proprietarioServico(dto.proprietarioServico())
                .build();
    }
}
