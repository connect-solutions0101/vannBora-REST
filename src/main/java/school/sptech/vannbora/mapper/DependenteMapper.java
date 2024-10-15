package school.sptech.vannbora.mapper;

import java.time.LocalDate;

import school.sptech.vannbora.dto.dependente.DependenteRequestDto;
import school.sptech.vannbora.dto.dependente.DependenteResponseDto;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.enums.Turno;

public class DependenteMapper {

    public static DependenteResponseDto toDependenteResponseDto(Dependente dependente) {
        return DependenteResponseDto.builder()
            .id(dependente.getId())
            .nome(dependente.getNome())
            .dataNascimento(dependente.getDataNascimento().toString())
            .turno(dependente.getTurno().getDescricao())
            .condicao(dependente.getCondicao())
            .turma(dependente.getTurma())
            .proprietarioServicoId(dependente.getProprietarioServico().getId())
            .escolaId(dependente.getEscola().getId())
            .build();
    }

    public static Dependente toDependente(DependenteRequestDto dependenteResponseDto) {
        return Dependente.builder()
            .nome(dependenteResponseDto.nome())
            .dataNascimento(LocalDate.parse(dependenteResponseDto.dataNascimento()))
            .turno(Turno.valueOf(dependenteResponseDto.turno()))
            .condicao(dependenteResponseDto.condicao())
            .turma(dependenteResponseDto.turma())
            .build();
    }
}
