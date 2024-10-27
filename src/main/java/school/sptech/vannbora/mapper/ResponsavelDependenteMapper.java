package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.responsaveldependente.ResponsavelOnlyResponseDto;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteEscolaResponseDto;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteRequestDto;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteResponseDto;
import school.sptech.vannbora.entidade.ResponsavelDependente;

public class ResponsavelDependenteMapper {
    public static ResponsavelDependenteResponseDto toResponseDto(ResponsavelDependente responsavelDependente){
        return ResponsavelDependenteResponseDto.builder()
            .responsavelId(responsavelDependente.getResponsavel().getId())
            .dependenteId(responsavelDependente.getDependente().getId())
            .tipoResponsavel(responsavelDependente.getTipoResponsavel())
            .responsavel(
                ResponsavelMapper.toResponseDto(responsavelDependente.getResponsavel())
            )
            .dependente(
                DependenteMapper.toDependenteResponseDto(responsavelDependente.getDependente())
            )
            .build();
    }
    
    public static ResponsavelOnlyResponseDto toResponsavelResponseDto(ResponsavelDependente responsavelDependente){
        return ResponsavelOnlyResponseDto.builder()
            .responsavelId(responsavelDependente.getResponsavel().getId())
            .dependenteId(responsavelDependente.getDependente().getId())
            .tipoResponsavel(responsavelDependente.getTipoResponsavel())
            .responsavel(
                ResponsavelMapper.toResponseDto(responsavelDependente.getResponsavel())
            )
            .build();
    }

    public static ResponsavelDependenteEscolaResponseDto toResponsavelDependenteEscolaResponseDto (ResponsavelDependente responsavelDependente){
        return ResponsavelDependenteEscolaResponseDto.builder()
            .responsavelId(responsavelDependente.getResponsavel().getId())
            .dependenteId(responsavelDependente.getDependente().getId())
            .tipoResponsavel(responsavelDependente.getTipoResponsavel())
            .responsavel(
                ResponsavelMapper.toResponseDto(responsavelDependente.getResponsavel())
            )
            .dependente(
                DependenteMapper.toDependenteEscolaResponseDto(responsavelDependente.getDependente())
            )
            .build();
    }

    public static ResponsavelDependente toEntity(ResponsavelDependenteRequestDto responsavelDependente){
        return ResponsavelDependente.builder()
            .tipoResponsavel(responsavelDependente.tipoResponsavel())
            .build();
    }
}
