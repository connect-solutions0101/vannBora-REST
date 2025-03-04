package school.sptech.vannbora.dto.trajeto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import school.sptech.vannbora.entidade.ProprietarioServico;
import school.sptech.vannbora.entidade.TrajetoDependente;
import school.sptech.vannbora.enums.Periodo;

import java.util.List;

@Builder
public record TrajetoRequestDto(

        @NotBlank
        String nome,

        @NotBlank
        Periodo periodo,

        @NotBlank
        List<TrajetoDependente> trajetoDependentes,

        @NotBlank
        ProprietarioServico proprietarioServico
) {
}
