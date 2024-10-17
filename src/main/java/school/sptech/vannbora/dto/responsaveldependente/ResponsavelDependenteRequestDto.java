package school.sptech.vannbora.dto.responsaveldependente;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import school.sptech.vannbora.enums.TipoResponsavel;

public record ResponsavelDependenteRequestDto(
    @Positive(message = "O id do responsável deve ser um número positivo")
    int responsavelId,
    
    @Positive(message = "O id do dependente deve ser um número positivo")
    int dependenteId,
    
    @NotNull(message = "O tipo do responsável não pode ser nulo")
    TipoResponsavel tipoResponsavel
) {}
