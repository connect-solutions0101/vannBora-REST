package school.sptech.vannbora.dto.dependente;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import school.sptech.vannbora.dto.escola.EscolaResponseDto;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelOnlyResponseDto;
import school.sptech.vannbora.enums.Pago;

@Builder
public record DependenteEscolaResponsaveisResponseDto(
    int id,
    String nome,
    LocalDate dataNascimento,
    LocalDate dataCadastro,
    String turno,
    String condicao,
    String turma,
    EscolaResponseDto escola,
    List<ResponsavelOnlyResponseDto> responsaveis,
    Pago ultimaFaturaPaga,
    Integer ultimaFaturaId
) {
    
}
