package school.sptech.vannbora.dto.registroFatura;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record RegistroFaturaRequestDto(

        @Positive
        @NotNull
        int faturaId,

        @NotNull
        boolean pago,

        @PastOrPresent
        LocalDate dataPagamento
) {
    
}
