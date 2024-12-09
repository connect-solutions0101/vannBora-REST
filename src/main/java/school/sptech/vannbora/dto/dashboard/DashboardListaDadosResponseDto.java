package school.sptech.vannbora.dto.dashboard;

import java.math.BigDecimal;
import java.util.List;

public record DashboardListaDadosResponseDto(
    List<InnerResponseDto> esperados,
    List<InnerResponseDto> realizados
) {
    public record InnerResponseDto(
        Integer periodo,
        BigDecimal valor
    ) {}
}
