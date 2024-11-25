package school.sptech.vannbora.dto.fatura;

public record FaturaCsvDto (
        String nomeResponsavel,

        String nomeDependente,

        String parentescoResponsavel,

        int diaPagamento,

        Double valorPagamento,

        Boolean pago
) {

}