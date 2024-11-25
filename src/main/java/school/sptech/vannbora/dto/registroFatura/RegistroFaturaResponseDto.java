package school.sptech.vannbora.dto.registroFatura;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record RegistroFaturaResponseDto(
        int id,
        boolean pago,
        LocalDate dataPagamento
) {
    
}
