package school.sptech.vannbora.dto.registroembarquedesembarque;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import school.sptech.vannbora.enums.EmbarqueDesembarque;

import java.time.LocalDateTime;

public record RegistroEmbarqueDesembarqueRequestDto(

        @NotNull(message = "a data não pode ser nula.")
        @PastOrPresent(message = "a data deve ser na data presente ou passada.")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataHora,

        @NotNull(message = "O tipo não pode ser nulo.")
        EmbarqueDesembarque tipo,

        @NotNull(message = "O id do reponsável não pode ser nulo.")
        @Positive(message = "O id do responsável deve ser positivo.")
        int responsavelId,

        @NotNull(message = "O id do dependente não pode ser nulo.")
        @Positive(message = "O id do dependente deve ser positivo.")
        int dependenteId
) {
}
