package school.sptech.vannbora.dto.fatura;

import java.time.LocalDate;

import lombok.Builder;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteResponseDto;
import school.sptech.vannbora.enums.Pago;

@Builder
public record FaturaResponseDto(
        int id,
        double valor,
        Pago pago,
        LocalDate dataVencimento,
        Integer diaPagamento,
        Integer quantidadeParcelas,
        ResponsavelDependenteResponseDto responsavelDependenteId
) {
    
}
