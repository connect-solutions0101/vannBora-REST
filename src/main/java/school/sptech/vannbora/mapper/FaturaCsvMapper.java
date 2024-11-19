package school.sptech.vannbora.mapper;

import school.sptech.vannbora.dto.fatura.FaturaCsvDto;
import school.sptech.vannbora.entidade.Fatura;

public class FaturaCsvMapper {
    public static FaturaCsvDto toCsvDto(Fatura fatura) {
        return new FaturaCsvDto(
                fatura.getResponsavelDependente().getResponsavel().getNome(),
                fatura.getResponsavelDependente().getDependente().getNome(),
                fatura.getResponsavelDependente().getTipoResponsavel().toString(),
                fatura.getDiaPagamento(),
                fatura.getValor(),
                null
        );
    }
}