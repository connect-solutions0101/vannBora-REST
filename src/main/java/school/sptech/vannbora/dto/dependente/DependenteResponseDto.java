package school.sptech.vannbora.dto.dependente;

import java.time.LocalDate;

import lombok.Builder;
import school.sptech.vannbora.enums.Turno;

@Builder
public record DependenteResponseDto(
    int id,
    String nome,
    LocalDate dataNascimento,
    LocalDate dataCadastro,
    Turno turno,
    String condicao,
    String turma,
    int proprietarioServicoId,
    int escolaId
) {
    
}
