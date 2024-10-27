package school.sptech.vannbora.dto.registroembarquedesembarque;

import school.sptech.vannbora.enums.EmbarqueDesembarque;

import java.time.LocalDateTime;

public record RegistroEmbarqueDesembarqueResponseDto(
        int id,
        LocalDateTime dataHora,
        EmbarqueDesembarque tipo,
        int responsavelId,
        int dependenteId
) {
}
