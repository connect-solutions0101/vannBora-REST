package school.sptech.vannbora.dto.fatura;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record FaturaRequestDto(

        @NotNull
        Double valor,

        @NotNull
        @Positive
        int diaPagamento,

        @NotNull
        @Positive
        int quantidadeParcelas,

        @NotNull
        @Positive
        Integer responsavelId,

        @NotNull
        @Positive
        Integer dependenteId
) {

}