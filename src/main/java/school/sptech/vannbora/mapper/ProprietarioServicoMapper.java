package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.proprietario.ProprietarioServicoResponseDto;
import school.sptech.vannbora.entidade.ProprietarioServico;

public class ProprietarioServicoMapper {
    public static ProprietarioServicoResponseDto toProprietarioServicoResponseDto(ProprietarioServico proprietario){
        if(proprietario == null) return null;

        return ProprietarioServicoResponseDto.builder()
                .id(proprietario.getId())
                .nome(proprietario.getNome())
                .email(proprietario.getEmail())
                .role(proprietario.getRole())
                .build();
    }
}
