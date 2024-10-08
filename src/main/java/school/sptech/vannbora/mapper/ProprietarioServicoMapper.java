package school.sptech.vannbora.mapper;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoRequestDto;
import school.sptech.vannbora.dto.proprietario.ProprietarioServicoResponseDto;
import school.sptech.vannbora.entidade.ProprietarioServico;

@Component
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

    public static ProprietarioServico toProprietarioServico(ProprietarioServicoRequestDto dto){
        if(dto == null){
            return null;
        }

        return new ProprietarioServico(
                dto.nome(),
                dto.email(),
                dto.senha(),
                dto.role()
        );
    }

    public static ProprietarioServico toProprietarioServicoAtualizar(int id, ProprietarioServicoRequestDto dto){
        if(dto == null){
            return null;
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dto.senha());
        return new ProprietarioServico(
                id,
                dto.nome(),
                dto.email(),
                senhaCriptografada,
                dto.role()
        );
    }
}
