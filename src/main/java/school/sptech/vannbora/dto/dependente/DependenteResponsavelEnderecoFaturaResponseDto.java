package school.sptech.vannbora.dto.dependente;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import school.sptech.vannbora.dto.escola.EscolaResponseDto;
import school.sptech.vannbora.dto.fatura.FaturaResponseDto;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelOnlyResponseDto;
import school.sptech.vannbora.enums.Turno;

@Builder
public record DependenteResponsavelEnderecoFaturaResponseDto(
    Integer id,
    String nome,
    LocalDate dataNascimento,
    Turno turno,
    String condicao,
    String turma,
    EscolaResponseDto escola,
    List<ResponsavelOnlyResponseDto> responsaveis,
    FaturaResponseDto fatura
) {

    
}
