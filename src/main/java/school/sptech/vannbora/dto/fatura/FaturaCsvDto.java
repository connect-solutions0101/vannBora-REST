package school.sptech.vannbora.dto.fatura;

import school.sptech.vannbora.enums.Pago;

public record FaturaCsvDto (
        String nomeResponsavel,

        String nomeDependente,

        String parentescoResponsavel,

        int diaPagamento,

        Double valorPagamento,

        Pago pago
) {

}