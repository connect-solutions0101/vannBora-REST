package school.sptech.vannbora.dto.dependente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import school.sptech.vannbora.enums.Turno;

import java.time.LocalDate;

@Builder
public record DependenteRequestDto(
    @NotBlank
    String nome,

    @Past
    @NotNull
    LocalDate dataNascimento,

    @NotNull
    Turno turno,
    
    @NotBlank
    String condicao,

    @NotBlank
    String turma,

    @NotNull
    @Positive
    int proprietarioServicoId,

    @NotNull
    @Positive
    int escolaId
) {
    
}
