package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.escola.EscolaAlunosResponseDto;
import school.sptech.vannbora.dto.escola.EscolaRequestDto;
import school.sptech.vannbora.dto.escola.EscolaResponseDto;
import school.sptech.vannbora.entidade.Escola;

public class EscolaMapper {

    public static EscolaResponseDto toEscolaResponseDto(Escola escola){
        if(escola == null){
            return null;
        }

        return new EscolaResponseDto(
                escola.getNome(),
                escola.getNomeResponsavel(),
                escola.getTelefone(),
                escola.getTelefoneResponsavel(),
                EnderecoMapper.toEnderecoResponseDto(escola.getEndereco())
        );
    }

    public static EscolaAlunosResponseDto toEscolaResponseDto(Escola escola, int quantidadeAlunos, int pagamentosPendentes){
        if(escola == null){
            return null;
        }

        return new EscolaAlunosResponseDto(
                escola.getId(),
                escola.getNome(),
                escola.getNomeResponsavel(),
                escola.getTelefone(),
                escola.getTelefoneResponsavel(),
                EnderecoMapper.toEnderecoResponseDto(escola.getEndereco()),
                quantidadeAlunos,
                pagamentosPendentes
        );
    }

    public static Escola toEscola(EscolaRequestDto dto){
        if(dto == null){
            return null;
        }

        return Escola.builder()
                .nome(dto.nome())
                .nomeResponsavel(dto.nomeResponsavel())
                .telefone(dto.telefone())
                .telefoneResponsavel(dto.telefoneResponsavel())
                .build();
    }
}
