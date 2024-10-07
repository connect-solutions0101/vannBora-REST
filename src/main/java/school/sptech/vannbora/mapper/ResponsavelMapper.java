package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.responsavel.ResponsavelRequestDto;
import school.sptech.vannbora.dto.responsavel.ResponsavelResponseDto;
import school.sptech.vannbora.entidade.Responsavel;


public class ResponsavelMapper {

    public static Responsavel toEntity(ResponsavelRequestDto responsavel){
        return Responsavel.builder()
                .nome(responsavel.nome())
                .telefone(responsavel.telefone())
                .parentesco(responsavel.parentesco())
                .build();
    }
    
    public static ResponsavelResponseDto toResponseDto(Responsavel responsavel){
        return ResponsavelResponseDto.builder()
                .id(responsavel.getId())
                .nome(responsavel.getNome())
                .telefone(responsavel.getTelefone())
                .parentesco(responsavel.getParentesco())
                .endereco(EnderecoMapper.toEnderecoResponseDto(responsavel.getEndereco()))
                .proprietarioServico(ProprietarioServicoMapper.toProprietarioServicoResponseDto(responsavel.getProprietarioServico()))
                .build();
    }
}
