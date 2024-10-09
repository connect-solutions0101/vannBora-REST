package school.sptech.vannbora.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoRequestDto;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoResponseDto;
import school.sptech.vannbora.entidade.ProprietarioServico;

public class ProprietarioServicoMapper {
    public static ProprietarioServicoResponseDto toProprietarioServicoResponseDto(ProprietarioServico proprietario){
        if(proprietario == null) return null;

        return ProprietarioServicoResponseDto.builder()
                .id(proprietario.getId())
                .nome(proprietario.getNome())
                .cpf(proprietario.getCpf())
                .email(proprietario.getEmail())
                .role(proprietario.getRole())
                .build();
    }

    public static ProprietarioServico toProprietarioServico(ProprietarioServicoRequestDto dto){
        if(dto == null){
            return null;
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        return ProprietarioServico.builder()
                .nome(dto.nome())
                .email(dto.email())
                .cpf(dto.cpf())
                .senha(senhaCriptografada)
                .role(dto.role())
                .build();
    }
}
