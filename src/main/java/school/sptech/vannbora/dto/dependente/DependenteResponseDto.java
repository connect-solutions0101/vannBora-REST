package school.sptech.vannbora.dto.dependente;

import lombok.Builder;

@Builder
public record DependenteResponseDto(
    int id,
    String nome,
    String dataNascimento,
    String turno,
    String condicao,
    String turma,
    int proprietarioServicoId,
    int escolaId
) {
    
}
