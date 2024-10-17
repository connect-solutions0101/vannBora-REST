package school.sptech.vannbora.mapper;


import school.sptech.vannbora.dto.dependente.DependenteRequestDto;
import school.sptech.vannbora.dto.dependente.DependenteResponseDto;
import school.sptech.vannbora.entidade.Dependente;

public class DependenteMapper {

    public static DependenteResponseDto toDependenteResponseDto(Dependente dependente) {
        return DependenteResponseDto.builder()
            .id(dependente.getId())
            .nome(dependente.getNome())
            .dataNascimento(dependente.getDataNascimento())
            .dataCadastro(dependente.getDataCadastro())
            .turno(dependente.getTurno())
            .condicao(dependente.getCondicao())
            .turma(dependente.getTurma())
            .proprietarioServicoId(dependente.getProprietarioServico().getId())
            .escolaId(dependente.getEscola().getId())
            .build();
    }

    public static Dependente toDependente(DependenteRequestDto dependenteResponseDto) {
        return Dependente.builder()
            .nome(dependenteResponseDto.nome())
            .dataNascimento(dependenteResponseDto.dataNascimento())
            .turno(dependenteResponseDto.turno())
            .condicao(dependenteResponseDto.condicao())
            .turma(dependenteResponseDto.turma())
            .build();
    }
}
