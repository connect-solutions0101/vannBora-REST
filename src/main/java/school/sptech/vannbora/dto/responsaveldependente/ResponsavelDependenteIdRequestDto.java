package school.sptech.vannbora.dto.responsaveldependente;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ResponsavelDependenteIdRequestDto(
        @NotNull
        @Positive(message = "O id do responsável deve ser um número positivo")
        Integer idResponsavel,

        @NotNull
        @Positive(message = "O id do dependente deve ser um número positivo")
        Integer idDependente
) {
}
