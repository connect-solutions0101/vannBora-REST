package school.sptech.vannbora.mapper;


import java.time.LocalDate;
import java.util.stream.Collectors;

import school.sptech.vannbora.dto.dependente.DependenteEscolaResponsaveisResponseDto;
import school.sptech.vannbora.dto.dependente.DependenteRequestDto;
import school.sptech.vannbora.dto.dependente.DependenteResponsavelEnderecoFaturaRequestDto;
import school.sptech.vannbora.dto.dependente.DependenteResponsavelEnderecoFaturaResponseDto;
import school.sptech.vannbora.dto.dependente.DependenteResponseDto;
import school.sptech.vannbora.entidade.Dependente;
import school.sptech.vannbora.entidade.Endereco;
import school.sptech.vannbora.entidade.RegistroFatura;
import school.sptech.vannbora.entidade.Responsavel;
import school.sptech.vannbora.entidade.ResponsavelDependente;
import school.sptech.vannbora.entidade.ResponsavelDependente.ResponsavelDependenteId;
import school.sptech.vannbora.enums.TipoResponsavel;
import school.sptech.vannbora.enums.Turno;

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
            .escola(EscolaMapper.toEscolaResponseDto(dependente.getEscola()))
            .build();
    }

    public static DependenteEscolaResponsaveisResponseDto toDependenteEscolaResponseDto(Dependente dependente) {
        return DependenteEscolaResponsaveisResponseDto.builder()
            .id(dependente.getId())
            .nome(dependente.getNome())
            .dataNascimento(dependente.getDataNascimento())
            .dataCadastro(dependente.getDataCadastro())
            .turno(dependente.getTurno().getDescricao())
            .condicao(dependente.getCondicao())
            .turma(dependente.getTurma())
            .escola(
                EscolaMapper.toEscolaResponseDto(dependente.getEscola())
            )
            .responsaveis(
                dependente.getResponsaveis().stream()
                    .map(ResponsavelDependenteMapper::toResponsavelResponseDto)
                    .collect(Collectors.toList())
            )
            .ultimaFaturaPaga(dependente.getResponsaveis().stream().filter(r -> r.getTipoResponsavel().equals(TipoResponsavel.FINANCEIRO)).findFirst().get().getFatura().get(0).getRegistroFatura().stream().filter(rf -> rf.getDataPagamento().getMonthValue() == LocalDate.now().getMonthValue()).findFirst().orElse(new RegistroFatura(null, null, null, null)).getPago())
            .ultimaFaturaId(dependente.getResponsaveis().stream().filter(r -> r.getTipoResponsavel().equals(TipoResponsavel.FINANCEIRO)).findFirst().get().getFatura().get(0).getRegistroFatura().stream().filter(rf -> rf.getDataPagamento().getMonthValue() == LocalDate.now().getMonthValue()).findFirst().orElse(new RegistroFatura(null, null, null, null)).getId())
            .build();
    }

    public static Dependente toDependente(DependenteRequestDto dependenteRequestDto) {
        return Dependente.builder()
            .nome(dependenteRequestDto.nome())
            .dataNascimento(dependenteRequestDto.dataNascimento())
            .turno(dependenteRequestDto.turno())
            .condicao(dependenteRequestDto.condicao())
            .turma(dependenteRequestDto.turma())
            .build();
    }

    public static Dependente toDependente(DependenteResponsavelEnderecoFaturaRequestDto dependenteRequestDto) {
        return Dependente.builder()
            .nome(dependenteRequestDto.nome())
            .dataNascimento(LocalDate.parse(dependenteRequestDto.dataNascimento()))
            .turno(Turno.valueOf(dependenteRequestDto.turno()))
            .condicao(dependenteRequestDto.condicao())
            .turma(dependenteRequestDto.turma())
            .responsaveis(
                dependenteRequestDto.responsaveis().stream()
                    .map((responsavel) -> {
                        return responsavel != null ? ResponsavelDependente.builder()
                        .id(ResponsavelDependenteId.builder()
                            .responsavelId(responsavel.responsavel().id())
                            .dependenteId(responsavel.dependenteId())
                            .build())
                        .responsavel(Responsavel.builder()
                            .id(responsavel.responsavel().id())
                            .nome(responsavel.responsavel().nome())
                            .parentesco(responsavel.responsavel().parentesco())
                            .cpf(responsavel.responsavel().cpf())
                            .telefone(responsavel.responsavel().telefone())
                            .endereco(Endereco.builder()
                                .id(responsavel.responsavel().endereco().id())
                                .cep(responsavel.responsavel().endereco().cep())
                                .logradouro(responsavel.responsavel().endereco().logradouro())
                                .numero(responsavel.responsavel().endereco().numero())
                                .bairro(responsavel.responsavel().endereco().bairro())
                                .cidade(responsavel.responsavel().endereco().cidade())
                                .pontoReferencia(responsavel.responsavel().endereco().pontoReferencia())
                                .build())
                            .build())
                        .tipoResponsavel(TipoResponsavel.valueOf(responsavel.tipoResponsavel()))
                        .build() : null;
                    })
                    .collect(Collectors.toList())
            ).build();
    }

    public static DependenteResponsavelEnderecoFaturaResponseDto toDependenteResponsavelEnderecoFaturaResponseDto(Dependente dependente) {
        return DependenteResponsavelEnderecoFaturaResponseDto.builder()
            .id(dependente.getId())
            .nome(dependente.getNome())
            .dataNascimento(dependente.getDataNascimento())
            .turno(dependente.getTurno())
            .condicao(dependente.getCondicao())
            .turma(dependente.getTurma())
            .escola(
                EscolaMapper.toEscolaResponseDto(dependente.getEscola())
            )
            .responsaveis(
                dependente.getResponsaveis().stream()
                    .map(ResponsavelDependenteMapper::toResponsavelResponseDto)
                    .collect(Collectors.toList())
            )
            .fatura(
                FaturaMapper.toFaturaResponseDto(dependente.getResponsaveis().stream().filter(r -> r.getTipoResponsavel().equals(TipoResponsavel.FINANCEIRO)).findFirst().get().getFatura().get(0))
            )
            .build();
    }
}
