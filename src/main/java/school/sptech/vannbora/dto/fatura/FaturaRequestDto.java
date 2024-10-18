package school.sptech.vannbora.dto.fatura;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FaturaRequestDto(
   
    @NotNull
    Double valor,

    Boolean pago,

    @NotNull
    @Future
    LocalDate dataVencimento,

    @NotNull
    @Positive
    Integer responsavelId,

    @NotNull
    @Positive
    Integer dependenteId
) {
    
}
