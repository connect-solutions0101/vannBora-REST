package school.sptech.vannbora.dto.fatura;

import java.time.LocalDate;

public record FaturaCsvDto (
        String nomeResponsavel,

        String nomeDependente,

        String parentescoResponsavel,

        LocalDate dataPagamento,

        Double valorPagamento,

        Boolean pago
) {

}