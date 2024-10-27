package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.registroembarquedesembarque.RegistroEmbarqueDesembarqueRequestDto;
import school.sptech.vannbora.dto.registroembarquedesembarque.RegistroEmbarqueDesembarqueResponseDto;
import school.sptech.vannbora.entidade.RegistroEmbarqueDesembarque;

public class RegistroEmbarqueDesembarqueMapper {

    public static RegistroEmbarqueDesembarqueResponseDto toResponseDto(RegistroEmbarqueDesembarque entidade){
        if(entidade == null){
            return null;
        }

        return new RegistroEmbarqueDesembarqueResponseDto(
                entidade.getId(),
                entidade.getDataHora(),
                entidade.getTipo(),
                entidade.getResponsavelDependente()
        );
    }

    public static RegistroEmbarqueDesembarque toEntity(RegistroEmbarqueDesembarqueRequestDto dto){
        if(dto == null){
            return null;
        }

        return RegistroEmbarqueDesembarque.builder()
                .dataHora(dto.dataHora())
                .tipo(dto.tipo())
                .build();
    }
}
