package school.sptech.vannbora.dto.trajeto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import school.sptech.vannbora.dto.responsaveldependente.ResponsavelDependenteIdRequestDto;
import school.sptech.vannbora.enums.Periodo;

import java.util.List;

@Builder
public record TrajetoRequestDto(

        @NotBlank
        String nome,

        @NotNull
        Periodo periodo,

        @NotNull
        List<ResponsavelDependenteIdRequestDto> trajetoDependentes,

        @NotNull
        @Positive
        Integer proprietarioServicoId
) {
}
